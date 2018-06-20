package com.kk.worldcup2018.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.dagger.DaggerWorldCupComponent;
import com.kk.worldcup2018.data.WorldCupFetcher;
import com.kk.worldcup2018.model.Team;

import java.util.ArrayList;
import java.util.Comparator;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class TeamsFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";

    @Inject
    WorldCupFetcher worldCupFetcher;

    private int columnCount = 1;
    private RecyclerView recyclerView;


    public TeamsFragment() {
        /**
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
        if (getArguments() != null) {
            columnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    private void injectDependencies() {
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
            if (recyclerView.getAdapter() == null) {
                recyclerView.setAdapter(new TeamsRecyclerViewAdapter(new ArrayList<>()));
            }
        }
    }

    private void fetchTeams() {
        worldCupFetcher.fetchTeams(teams -> {
            teams.sort(Comparator.comparing(Team::getName));
            ((TeamsRecyclerViewAdapter) recyclerView.getAdapter()).setTeams(teams);
            recyclerView.getAdapter().notifyDataSetChanged();
        });
        worldCupFetcher.fetchFixtures(fixtures -> {
            Timber.d(fixtures.toString());
        });
    }

}
