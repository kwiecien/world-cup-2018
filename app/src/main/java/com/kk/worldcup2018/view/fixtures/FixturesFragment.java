package com.kk.worldcup2018.view.fixtures;

import android.annotation.SuppressLint;
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

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.dagger.DaggerWorldCupComponent;
import com.kk.worldcup2018.database.AppDatabase;
import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.view.RecyclerViewFragment;
import com.kk.worldcup2018.view.support.FixtureStatusItemDecoration;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.schedulers.Schedulers;

import static com.kk.worldcup2018.utils.Collections.isNotEmpty;

public class FixturesFragment extends RecyclerViewFragment {

    private AppDatabase db;

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
        db = AppDatabase.getInstance(getContext().getApplicationContext());
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
            recyclerView.setAdapter(new FixturesRecyclerViewAdapter(getContext(), new ArrayList<>()));
        }
    }

    @SuppressLint("CheckResult")
    private void fetchFixtures() {
        Observable.just(db)
                .subscribeOn(Schedulers.io())
                .subscribe(appDatabase -> {
                    List<Fixture> dbFixtures = fetchDbFixtures();
                    if (isNotEmpty(dbFixtures)) {
                        displayOnUiThread(dbFixtures);
                    } else {
                        fetchApiFixtures();
                    }
                });
        worldCupFetcher.fetchFixtures(this::updateUi);
    }

    private List<Fixture> fetchDbFixtures() {
        return db.fixtureDao().findFixtures();
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
        recyclerView.getAdapter().notifyDataSetChanged();
    }

    public void addLeftVerticalDrawable(@Nullable Context context) {
        if (context == null) {
            return;
        }
        Drawable timed = ContextCompat.getDrawable(context, R.drawable.vertical_line_timed);
        Drawable inPlay = ContextCompat.getDrawable(context, R.drawable.vertical_line_in_play);
        Drawable finished = ContextCompat.getDrawable(context, R.drawable.vertical_line_finished);
        recyclerView.addItemDecoration(new FixtureStatusItemDecoration(timed, inPlay, finished));
    }

}
