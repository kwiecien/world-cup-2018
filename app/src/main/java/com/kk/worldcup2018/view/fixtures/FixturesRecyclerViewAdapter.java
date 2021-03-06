package com.kk.worldcup2018.view.fixtures;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Fixture;

import java.util.List;

import timber.log.Timber;

import static com.kk.worldcup2018.model.Fixture.Status.FINISHED;
import static com.kk.worldcup2018.model.Fixture.Status.IN_PLAY;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Fixture}
 */
public class FixturesRecyclerViewAdapter extends RecyclerView.Adapter<FixturesRecyclerViewAdapter.FixturesViewHolder> {

    public static final int TIMED_VIEW_TYPE = 0;
    public static final int IN_PLAY_VIEW_TYPE = 1;
    public static final int FINISHED_VIEW_TYPE = 2;
    private final Context context;
    private List<Fixture> fixtures;

    FixturesRecyclerViewAdapter(Context context, List<Fixture> fixtures) {
        this.context = context;
        this.fixtures = fixtures;
    }

    @NonNull
    @Override
    public FixturesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = selectViewType(parent, viewType);
        return new FixturesViewHolder(view);
    }

    private View selectViewType(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if (viewType == TIMED_VIEW_TYPE) {
            return inflater.inflate(R.layout.list_item_fixture_timed, parent, false);
        } else if (viewType == IN_PLAY_VIEW_TYPE) {
            return inflater.inflate(R.layout.list_item_fixture_in_play, parent, false);
        }
        return inflater.inflate(R.layout.list_item_fixture_finished, parent, false);
    }

    @Override
    public void onBindViewHolder(@NonNull final FixturesViewHolder holder, int position) {
        holder.fixture = fixtures.get(position);
        holder.homeTeam.setText(fixtures.get(position).getHomeTeamName());
        holder.awayTeam.setText(fixtures.get(position).getAwayTeamName());
        if (holder.getItemViewType() != TIMED_VIEW_TYPE) {
            holder.goalsHome.setText(String.valueOf(fixtures.get(position).getResult().getGoalsHomeTeam()));
            holder.goalsAway.setText(String.valueOf(fixtures.get(position).getResult().getGoalsAwayTeam()));
        }

        holder.mView.setOnClickListener(v -> {
            Timber.d("Fixture %s clicked", holder.fixture);
            ActivityOptionsCompat options = prepareTransition(holder);
            context.startActivity(FixtureActivity.newIntent(context, holder.fixture), options.toBundle());
        });
    }

    @NonNull
    private ActivityOptionsCompat prepareTransition(@NonNull FixturesViewHolder holder) {
        Pair<View, String> teamHome = Pair.create((View) holder.homeTeam, context.getString(R.string.transition_team_home));
        Pair<View, String> teamAway = Pair.create((View) holder.awayTeam, context.getString(R.string.transition_team_away));
        Pair<View, String> goalsHome = Pair.create((View) holder.goalsHome, context.getString(R.string.transition_goals_home));
        Pair<View, String> goalsAway = Pair.create((View) holder.goalsAway, context.getString(R.string.transition_goals_away));
        return ActivityOptionsCompat.makeSceneTransitionAnimation(
                (Activity) context, teamHome, teamAway, goalsHome, goalsAway);
    }

    @Override
    public int getItemViewType(int position) {
        if (fixtures.get(position).getStatus() == FINISHED) {
            return FINISHED_VIEW_TYPE;
        } else if (fixtures.get(position).getStatus() == IN_PLAY) {
            return IN_PLAY_VIEW_TYPE;
        } else {
            return TIMED_VIEW_TYPE;
        }
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
