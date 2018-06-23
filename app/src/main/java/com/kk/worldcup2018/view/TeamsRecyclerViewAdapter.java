package com.kk.worldcup2018.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Team;

import java.util.List;

import timber.log.Timber;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Team}
 */
public class TeamsRecyclerViewAdapter extends RecyclerView.Adapter<TeamsRecyclerViewAdapter.TeamsViewHolder> {

    private List<Team> teams;

    public TeamsRecyclerViewAdapter(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public TeamsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_team, parent, false);
        return new TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TeamsViewHolder holder, int position) {
        holder.team = teams.get(position);
        holder.idView.setText(teams.get(position).getName());
        holder.contentView.setText(teams.get(position).getCode());

        holder.mView.setOnClickListener(v ->
                Timber.d("Team %s clicked", holder.team)
        );
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public class TeamsViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView idView;
        public final TextView contentView;
        public Team team;

        public TeamsViewHolder(View view) {
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
