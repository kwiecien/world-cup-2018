package com.kk.worldcup2018.view.teams;

import android.annotation.SuppressLint;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.Tracker;
import com.kk.worldcup2018.FavoriteTeamService;
import com.kk.worldcup2018.R;
import com.kk.worldcup2018.dagger.DaggerWorldCupComponent;
import com.kk.worldcup2018.database.AppDatabase;
import com.kk.worldcup2018.databinding.FragmentTeamBinding;
import com.kk.worldcup2018.model.Player;
import com.kk.worldcup2018.model.Standings;
import com.kk.worldcup2018.model.Team;
import com.kk.worldcup2018.view.RecyclerViewFragment;
import com.kk.worldcup2018.view.support.GoogleAnalyticsUtils;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.kk.worldcup2018.utils.Collections.isNotEmpty;

public class TeamFragment extends RecyclerViewFragment {

    private static final String ARG_TEAM = "arg-team";
    private static final String TAG = TeamFragment.class.getSimpleName();
    private Team team;
    private AppDatabase db;
    private Tracker tracker;

    public TeamFragment() {
        /*
         * Mandatory empty constructor for the fragment manager to instantiate the
         * fragment (e.g. upon screen orientation changes).
         */
    }

    public static TeamFragment newInstance(@NonNull Team team) {
        TeamFragment teamFragment = new TeamFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_TEAM, Parcels.wrap(team));
        teamFragment.setArguments(bundle);
        return teamFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        tracker = GoogleAnalyticsUtils.initializeTracker(getActivity());
        db = AppDatabase.getInstance(getContext().getApplicationContext());
        if (getArguments() != null && getArguments().containsKey(ARG_TEAM)) {
            team = Parcels.unwrap(getArguments().getParcelable(ARG_TEAM));
            getActivity().setTitle(team.getName());
        }
    }

    @Override
    protected void injectDependencies() {
        DaggerWorldCupComponent.builder().build().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTeamBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_team, container, false);
        View view = binding.getRoot();
        binding.setTeam(team);
        setupRecyclerView(view.findViewById(R.id.players_list));
        setupFab(view);
        fetchPlayers();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        GoogleAnalyticsUtils.trackScreen(tracker, TAG);
    }

    private void setupRecyclerView(View view) {
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new PlayersRecyclerViewAdapter(new ArrayList<>()));
        }
    }

    private void setupFab(View view) {
        view.findViewById(R.id.fab).setOnClickListener(fab ->
                Observable.just(db)
                        .subscribeOn(Schedulers.io())
                        .subscribe(appDatabase -> {
                            Standings standings = db.standingsDao().findStandingsForTeam(team.getName());
                            FavoriteTeamService.startActionUpdateFavoriteTeamWidgets(getContext().getApplicationContext(),
                                    team, standings);
                        }));
    }

    @SuppressLint("CheckResult")
    private void fetchPlayers() {
        Observable.just(db)
                .subscribeOn(Schedulers.io())
                .subscribe(appDatabase -> {
                    List<Player> dbPlayers = fetchDbPlayers();
                    if (isNotEmpty(dbPlayers)) {
                        displayOnUiThread(dbPlayers);
                    } else {
                        fetchApiPlayers();
                    }
                });
    }

    private List<Player> fetchDbPlayers() {
        return db.playerDao().findPlayersForTeam(team.getName());
    }

    @SuppressLint("CheckResult")
    private void fetchApiPlayers() {
        worldCupFetcher.fetchPlayers(team.getTeamId(), fetchedPlayers -> {
            if (isNotEmpty(fetchedPlayers)) {
                Observable.just(db)
                        .subscribeOn(Schedulers.io())
                        .subscribe(appDatabase -> {
                            persistPlayers(fetchedPlayers);
                            displayOnUiThread(fetchedPlayers);
                        });
            }
        });
    }

    private void displayOnUiThread(List<Player> players) {
        getActivity().runOnUiThread(() -> updateUi(players));
    }

    private void persistPlayers(List<Player> players) {
        players.forEach(player -> player.setTeamName(team.getName()));
        db.playerDao().insertPlayers(players);
    }

    private void updateUi(List<Player> players) {
        PlayersRecyclerViewAdapter playersAdapter = (PlayersRecyclerViewAdapter) recyclerView.getAdapter();
        playersAdapter.setPlayers(players);
        addDecorationsToRecyclerView();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    @Override
    public void onStart() {
        super.onStart();
        TeamViewModelFactory modelFactory = new TeamViewModelFactory(db, team.getTeamId());
        final TeamViewModel viewModel = ViewModelProviders.of(getActivity(), modelFactory)
                .get(TeamViewModel.class);
        viewModel.getTeam().observe(this, new Observer<Team>() {
            @Override
            public void onChanged(@Nullable Team team) {
                viewModel.getTeam().removeObserver(this);
            }
        });
    }

}
