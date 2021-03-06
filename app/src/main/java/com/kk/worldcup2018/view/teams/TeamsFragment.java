package com.kk.worldcup2018.view.teams;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.Tracker;
import com.kk.worldcup2018.R;
import com.kk.worldcup2018.dagger.DaggerWorldCupComponent;
import com.kk.worldcup2018.database.AppDatabase;
import com.kk.worldcup2018.model.Team;
import com.kk.worldcup2018.view.MainViewModel;
import com.kk.worldcup2018.view.RecyclerViewFragment;
import com.kk.worldcup2018.view.support.GoogleAnalyticsUtils;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.kk.worldcup2018.utils.Collections.isNotEmpty;

public class TeamsFragment extends RecyclerViewFragment {

    private static final String TAG = TeamsFragment.class.getSimpleName();
    private static final String BUNDLE_RECYCLER_LAYOUT = "arg-recycler-view-position";
    private AppDatabase db;
    private Tracker tracker;
    private MainViewModel viewModel;

    public TeamsFragment() {
        /*
         * Mandatory empty constructor for the fragment manager to instantiate the
         * fragment (e.g. upon screen orientation changes).
         */
    }

    public static TeamsFragment newInstance() {
        return new TeamsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        tracker = GoogleAnalyticsUtils.initializeTracker(getActivity());
        db = AppDatabase.getInstance(getContext().getApplicationContext());
        viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
    }

    @Override
    protected void injectDependencies() {
        DaggerWorldCupComponent.builder().build().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_teams_list, container, false);
        setupRecyclerView(view);
        fetchTeams();
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        GoogleAnalyticsUtils.trackScreen(tracker, TAG);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable(BUNDLE_RECYCLER_LAYOUT, recyclerView.getLayoutManager().onSaveInstanceState());
    }

    @Override
    public void onViewStateRestored(@Nullable Bundle savedInstanceState) {
        super.onViewStateRestored(savedInstanceState);
        if (savedInstanceState != null) {
            Parcelable savedRecyclerLayoutState = savedInstanceState.getParcelable(BUNDLE_RECYCLER_LAYOUT);
            recyclerView.getLayoutManager().onRestoreInstanceState(savedRecyclerLayoutState);
        }
    }

    private void setupRecyclerView(View view) {
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new TeamsRecyclerViewAdapter(getContext(), new ArrayList<>()));
        }
    }

    @SuppressLint("CheckResult")
    private void fetchTeams() {
        fetchDbTeams().observe(this, teams -> {
            if (isNotEmpty(teams)) {
                updateUi(teams);
            } else {
                fetchApiTeams();
            }
        });
    }

    private LiveData<List<Team>> fetchDbTeams() {
        return viewModel.getTeams();
    }

    @SuppressLint("CheckResult")
    private void fetchApiTeams() {
        worldCupFetcher.fetchTeams(fetchedTeams -> {
            if (isNotEmpty(fetchedTeams)) {
                Observable.just(db)
                        .subscribeOn(Schedulers.io())
                        .subscribe(appDatabase -> {
                            persistTeams(fetchedTeams);
                            displayOnUiThread(fetchedTeams);
                        });
            }
        });
    }

    private void displayOnUiThread(List<Team> teams) {
        getActivity().runOnUiThread(() -> updateUi(teams));
    }

    private void persistTeams(List<Team> teams) {
        db.teamDao().insertTeams(teams);
    }

    private void updateUi(List<Team> teams) {
        teams.sort(Comparator.comparing(Team::getName));
        ((TeamsRecyclerViewAdapter) recyclerView.getAdapter()).setTeams(teams);
        addDecorationsToRecyclerView();
    }

}
