package com.kk.worldcup2018.view.groups;

import android.annotation.SuppressLint;
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
import com.kk.worldcup2018.database.AppDatabase;
import com.kk.worldcup2018.model.Group;
import com.kk.worldcup2018.view.RecyclerViewFragment;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.kk.worldcup2018.utils.Collections.isNotEmpty;

public class GroupsFragment extends RecyclerViewFragment {

    private AppDatabase db;

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
        db = AppDatabase.getInstance(getContext().getApplicationContext());
    }

    @Override
    protected void injectDependencies() {
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
            recyclerView.setAdapter(new GroupsRecyclerViewAdapter(context, new ArrayList<>()));
        }
    }

    @SuppressLint("CheckResult")
    private void fetchGroups() {
        Observable.just(db)
                .subscribeOn(Schedulers.io())
                .subscribe(appDatabase -> {
                    List<Group> dbGroups = fetchDbGroups();
                    if (isNotEmpty(dbGroups)) {
                        displayOnUiThread(dbGroups);
                    } else {
                        fetchApiGroups();
                    }
                });


    }

    private List<Group> fetchDbGroups() {
        return db.groupDao().findGroups();
    }

    @SuppressLint("CheckResult")
    private void fetchApiGroups() {
        worldCupFetcher.fetchGroups(fetchedGroups -> {
            if (isNotEmpty(fetchedGroups)) {
                Observable.just(db)
                        .subscribeOn(Schedulers.io())
                        .subscribe(appDatabase -> {
                            persistGroups(fetchedGroups);
                            displayOnUiThread(fetchedGroups);
                        });
            }
        });
    }

    private void displayOnUiThread(List<Group> groups) {
        getActivity().runOnUiThread(() -> updateUi(groups));
    }

    private void persistGroups(List<Group> groups) {
        db.groupDao().insertGroups(groups);
        db.standingsDao().insertStandings(groups.stream()
                .map(Group::getStandingsList)
                .flatMap(Collection::stream)
                .collect(Collectors.toList()));
    }

    private void updateUi(List<Group> groups) {
        ((GroupsRecyclerViewAdapter) recyclerView.getAdapter()).setGroups(groups);
        addDecorationsToRecyclerView();
        recyclerView.getAdapter().notifyDataSetChanged();
    }

}
