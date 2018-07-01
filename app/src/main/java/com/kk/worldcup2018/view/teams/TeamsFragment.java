package com.kk.worldcup2018.view.teams;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.dagger.DaggerWorldCupComponent;
import com.kk.worldcup2018.data.WorldCupFetcher;
import com.kk.worldcup2018.database.AppDatabase;
import com.kk.worldcup2018.model.Team;
import com.kk.worldcup2018.view.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TeamsFragment extends RecyclerViewFragment {

    private static final String ARG_COLUMN_COUNT = "column-count";

    private AppDatabase db;
    private int columnCount = 1;

    public TeamsFragment() {
        /*
         * Mandatory empty constructor for the fragment manager to instantiate the
         * fragment (e.g. upon screen orientation changes).
         */
    }

    public static TeamsFragment newInstance(int columnCount) {
        TeamsFragment fragment = new TeamsFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        db = AppDatabase.getInstance(getContext().getApplicationContext());
        if (getArguments() != null) {
            columnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
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

    private void setupRecyclerView(View view) {
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            if (columnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
            }
            recyclerView.setAdapter(new TeamsRecyclerViewAdapter(getContext(), new ArrayList<>()));
        }
    }

    private void fetchTeams() {
        new FetchTeamsAsyncTask(db, worldCupFetcher, this).execute();
    }

    private void updateUi(List<Team> teams) {
        teams.sort(Comparator.comparing(Team::getName));
        ((TeamsRecyclerViewAdapter) recyclerView.getAdapter()).setTeams(teams);
        addDecorationsToRecyclerView();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    private static class FetchTeamsAsyncTask extends AsyncTask<Void, Void, List<Team>> {
        private final AppDatabase db;
        private final WorldCupFetcher worldCupFetcher;
        private final TeamsFragment teamsFragment;

        FetchTeamsAsyncTask(AppDatabase db, WorldCupFetcher worldCupFetcher, TeamsFragment teamsFragment) {
            this.db = db;
            this.worldCupFetcher = worldCupFetcher;
            this.teamsFragment = teamsFragment;
        }

        @Override
        protected List<Team> doInBackground(Void... params) {
            return db.teamDao().findTeams();
        }

        @Override
        protected void onPostExecute(List<Team> teams) {
            if (teams != null && !teams.isEmpty()) {
                teamsFragment.updateUi(teams);
            } else {
                worldCupFetcher.fetchTeams(fetchedTeams -> {
                    new InsertTeamsAsyncTask(db).execute(fetchedTeams);
                    teamsFragment.updateUi(fetchedTeams);
                });
            }
        }
    }

    private static class InsertTeamsAsyncTask extends AsyncTask<List<Team>, Void, Void> {
        private final AppDatabase db;

        InsertTeamsAsyncTask(AppDatabase db) {
            this.db = db;
        }

        @Override
        protected Void doInBackground(List<Team>... teams) {
            db.teamDao().insertTeams(teams[0]);
            return null;
        }

    }

}
