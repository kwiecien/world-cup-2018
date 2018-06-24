package com.kk.worldcup2018.view;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Fixture;

import java.util.List;

import timber.log.Timber;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Fixture}
 */
public class FixturesRecyclerViewAdapter extends RecyclerView.Adapter<FixturesRecyclerViewAdapter.FixturesViewHolder> {

    private List<Fixture> fixtures;

    FixturesRecyclerViewAdapter(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    @NonNull
    @Override
    public FixturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_fixture, parent, false);
        return new FixturesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final FixturesViewHolder holder, int position) {
        holder.fixture = fixtures.get(position);
        holder.homeTeam.setText(fixtures.get(position).getHomeTeamName());
        holder.awayTeam.setText(fixtures.get(position).getAwayTeamName());
        holder.goalsHome.setText(String.valueOf(fixtures.get(position).getResult().getGoalsHomeTeam()));
        holder.goalsAway.setText(String.valueOf(fixtures.get(position).getResult().getGoalsAwayTeam()));

        holder.mView.setOnClickListener(v ->
                Timber.d("Fixture %s clicked", holder.fixture)
        );
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    @Override
    public int getItemCount() {
        return fixtures.size();
    }

    class FixturesViewHolder extends RecyclerView.ViewHolder {
        final View mView;
        final TextView homeTeam;
        final TextView awayTeam;
        final TextView goalsHome;
        final TextView goalsAway;
        Fixture fixture;

        FixturesViewHolder(View view) {
            super(view);
            mView = view;
            homeTeam = view.findViewById(R.id.home_team);
            awayTeam = view.findViewById(R.id.away_team);
            goalsHome = view.findViewById(R.id.goals_home);
            goalsAway = view.findViewById(R.id.goals_away);
        }

    }
}
