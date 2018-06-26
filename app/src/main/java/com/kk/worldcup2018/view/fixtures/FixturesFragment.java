package com.kk.worldcup2018.view.fixtures;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.dagger.DaggerWorldCupComponent;
import com.kk.worldcup2018.view.RecyclerViewFragment;

import java.util.ArrayList;

public class FixturesFragment extends RecyclerViewFragment {

    public FixturesFragment() {
        /*
         * Mandatory empty constructor for the fragment manager to instantiate the
         * fragment (e.g. upon screen orientation changes).
         */
    }

    public static FixturesFragment newInstance() {
        return new FixturesFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    @Override
    protected void injectDependencies() {
        DaggerWorldCupComponent.builder().build().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixtures_list, container, false);
        setupRecyclerView(view);
        fetchFixtures();
        return view;
    }

    private void setupRecyclerView(View view) {
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new FixturesRecyclerViewAdapter(new ArrayList<>()));
        }
    }

    private void fetchFixtures() {
        worldCupFetcher.fetchFixtures(fixtures -> {
            ((FixturesRecyclerViewAdapter) recyclerView.getAdapter()).setFixtures(fixtures);
            addDecorationsToRecyclerView();
            recyclerView.getAdapter().notifyDataSetChanged();
        });
    }

}
