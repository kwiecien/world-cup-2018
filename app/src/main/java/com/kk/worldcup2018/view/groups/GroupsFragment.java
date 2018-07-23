package com.kk.worldcup2018.view.groups;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.Tracker;
import com.kk.worldcup2018.R;
import com.kk.worldcup2018.dagger.DaggerWorldCupComponent;
import com.kk.worldcup2018.database.AppDatabase;
import com.kk.worldcup2018.model.Group;
import com.kk.worldcup2018.view.MainViewModel;
import com.kk.worldcup2018.view.RecyclerViewFragment;
import com.kk.worldcup2018.view.support.GoogleAnalyticsUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.kk.worldcup2018.utils.Collections.isNotEmpty;

public class GroupsFragment extends RecyclerViewFragment {

    private static final String TAG = GroupsFragment.class.getSimpleName();
    private AppDatabase db;
    private Tracker tracker;

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
        tracker = GoogleAnalyticsUtils.initializeTracker(getActivity());
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
            recyclerView.setAdapter(new GroupsRecyclerViewAdapter(context, new ArrayList<>()));
        }
    }

    @SuppressLint("CheckResult")
    private void fetchGroups() {
        fetchDbGroups().observe(this, groups -> {
            if (isNotEmpty(groups)) {
                fetchDbStandings(groups);
                updateUi(groups);
            } else {
                fetchApiGroups();
            }
        });
    }

    private LiveData<List<Group>> fetchDbGroups() {
        MainViewModel viewModel = ViewModelProviders.of(getActivity()).get(MainViewModel.class);
        return viewModel.getGroups();
    }

    private void fetchDbStandings(List<Group> groups) {
        for (Group group : groups) {
            db.standingsDao().findStandingsForGroup(group.getLetter())
                    .observe(this, group::setStandingsList);
        }
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
    }

}
