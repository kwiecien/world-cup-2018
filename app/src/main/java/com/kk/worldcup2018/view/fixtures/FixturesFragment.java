package com.kk.worldcup2018.view.fixtures;

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
import com.kk.worldcup2018.view.RecyclerViewFragment;
import com.kk.worldcup2018.view.support.LeftVerticalItemDecoration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FixturesFragment extends RecyclerViewFragment {

    private final List<Integer> colors = new ArrayList<>(Arrays.asList(
            android.R.color.holo_red_dark,
            android.R.color.holo_blue_dark));

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
            recyclerView.setAdapter(new FixturesRecyclerViewAdapter(getContext(), new ArrayList<>()));
        }
    }

    private void fetchFixtures() {
        worldCupFetcher.fetchFixtures(fixtures -> {
            ((FixturesRecyclerViewAdapter) recyclerView.getAdapter()).setFixtures(fixtures);
            addDecorationsToRecyclerView();
            addLeftVerticalDrawable(getContext(), colors);
            recyclerView.getAdapter().notifyDataSetChanged();
        });
    }

    public void addLeftVerticalDrawable(@Nullable Context context, @NonNull List<Integer> colors) {
        if (context == null) {
            return;
        }
        Drawable verticalLine1 = ContextCompat.getDrawable(context, R.drawable.vertical_line1);
        Drawable verticalLine2 = ContextCompat.getDrawable(context, R.drawable.vertical_line2);
        recyclerView.addItemDecoration(new LeftVerticalItemDecoration(verticalLine1, verticalLine2, colors));
    }

}
