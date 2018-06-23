package com.kk.worldcup2018.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.model.Team;

import java.util.List;

import timber.log.Timber;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Team}
 */
public class FixturesRecyclerViewAdapter extends RecyclerView.Adapter<FixturesRecyclerViewAdapter.FixturesViewHolder> {

    private List<Fixture> fixtures;

    public FixturesRecyclerViewAdapter(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    @Override
    public FixturesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_fixtures, parent, false);
        return new FixturesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final FixturesViewHolder holder, int position) {
        holder.fixture = fixtures.get(position);
        holder.idView.setText(fixtures.get(position).getHomeTeamName());
        holder.contentView.setText(fixtures.get(position).getAwayTeamName());

        holder.mView.setOnClickListener(v ->
                Timber.d("Fixture %s clicked", holder.fixture)
        );
    }

    public List<Fixture> getFixtures() {
        return fixtures;
    }

    public void setFixtures(List<Fixture> fixtures) {
        this.fixtures = fixtures;
    }

    @Override
    public int getItemCount() {
        return fixtures.size();
    }

    public class FixturesViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView idView;
        public final TextView contentView;
        public Fixture fixture;

        public FixturesViewHolder(View view) {
            super(view);
            mView = view;
            idView = view.findViewById(R.id.item_number);
            contentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }
}
