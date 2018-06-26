package com.kk.worldcup2018.view;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.dagger.DaggerWorldCupComponent;
import com.kk.worldcup2018.data.WorldCupFetcher;
import com.kk.worldcup2018.view.support.RecyclerViewUtils;

import java.util.ArrayList;

import javax.inject.Inject;

public class GroupsFragment extends Fragment {

    @Inject
    WorldCupFetcher worldCupFetcher;

    private RecyclerView recyclerView;

    public GroupsFragment() {
        /*
         * Mandatory empty constructor for the fragment manager to instantiate the
         * fragment (e.g. upon screen orientation changes).
         */
    }

    public static GroupsFragment newInstance() {
        return new GroupsFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
    }

    private void injectDependencies() {
        DaggerWorldCupComponent.builder().build().inject(this);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_groups_list, container, false);
        setupRecyclerView(view);
        fetchGroups();
        return view;
    }

    private void setupRecyclerView(View view) {
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            recyclerView = (RecyclerView) view;
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
            recyclerView.setAdapter(new GroupsRecyclerViewAdapter(new ArrayList<>()));
        }
    }

    private void fetchGroups() {
        worldCupFetcher.fetchGroups(groups -> {
            ((GroupsRecyclerViewAdapter) recyclerView.getAdapter()).setGroups(groups);
            addDecorationsToRecyclerView();
            recyclerView.getAdapter().notifyDataSetChanged();
        });
    }

    private void addDecorationsToRecyclerView() {
        RecyclerViewUtils.addDividerToRecyclerView(getContext(), recyclerView);
    }
}
