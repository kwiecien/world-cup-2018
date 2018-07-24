package com.kk.worldcup2018.view.fixtures;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.Tracker;
import com.kk.worldcup2018.R;
import com.kk.worldcup2018.dagger.DaggerWorldCupComponent;
import com.kk.worldcup2018.database.AppDatabase;
import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.view.MainViewModel;
import com.kk.worldcup2018.view.RecyclerViewFragment;
import com.kk.worldcup2018.view.support.FixtureStatusItemDecoration;
import com.kk.worldcup2018.view.support.GoogleAnalyticsUtils;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.kk.worldcup2018.utils.Collections.isNotEmpty;

public class FixturesFragment extends RecyclerViewFragment {

    private static final String TAG = FixturesFragment.class.getSimpleName();
    private AppDatabase db;
    private Tracker tracker;
    private MainViewModel viewModel;

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
        View view = inflater.inflate(R.layout.fragment_fixtures_list, container, false);
        setupRecyclerView(view);
        fetchFixtures();
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
            recyclerView.setAdapter(new FixturesRecyclerViewAdapter(getContext(), new ArrayList<>()));
        }
    }

    @SuppressLint("CheckResult")
    private void fetchFixtures() {
        fetchDbFixtures().observe(this, fixtures -> {
            if (isNotEmpty(fixtures)) {
                updateUi(fixtures);
            } else {
                fetchApiFixtures();
            }
        });
    }

    private LiveData<List<Fixture>> fetchDbFixtures() {
        return viewModel.getFixtures();
    }

    @SuppressLint("CheckResult")
    private void fetchApiFixtures() {
        worldCupFetcher.fetchFixtures(fetchedFixtures -> {
            if (isNotEmpty(fetchedFixtures)) {
                Observable.just(db)
                        .subscribeOn(Schedulers.io())
                        .subscribe(appDatabase -> {
                            persistFixtures(fetchedFixtures);
                            displayOnUiThread(fetchedFixtures);
                        });
            }
        });
    }

    private void displayOnUiThread(List<Fixture> fixtures) {
        getActivity().runOnUiThread(() -> updateUi(fixtures));
    }

    private void persistFixtures(List<Fixture> fixtures) {
        db.fixtureDao().insertFixtures(fixtures);
    }

    private void updateUi(List<Fixture> fixtures) {
        FixturesRecyclerViewAdapter fixturesAdapter = (FixturesRecyclerViewAdapter) recyclerView.getAdapter();
        fixturesAdapter.setFixtures(fixtures);
        addDecorationsToRecyclerView();
        addLeftVerticalDrawable(getContext());
    }

    private void addLeftVerticalDrawable(@Nullable Context context) {
        if (context == null) {
            return;
        }
        Drawable timed = ContextCompat.getDrawable(context, R.drawable.vertical_line_timed);
        Drawable inPlay = ContextCompat.getDrawable(context, R.drawable.vertical_line_in_play);
        Drawable finished = ContextCompat.getDrawable(context, R.drawable.vertical_line_finished);
        recyclerView.addItemDecoration(new FixtureStatusItemDecoration(timed, inPlay, finished));
    }

}
