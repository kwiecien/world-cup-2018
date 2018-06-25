package com.kk.worldcup2018.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Team;

import java.util.List;

import timber.log.Timber;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Team}
 */
public class TeamsRecyclerViewAdapter extends RecyclerView.Adapter<TeamsRecyclerViewAdapter.TeamsViewHolder> {

    private List<Team> teams;
    private Context context;

    TeamsRecyclerViewAdapter(Context context, List<Team> teams) {
        this.context = context;
        this.teams = teams;
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_team, parent, false);
        return new TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TeamsViewHolder holder, int position) {
        holder.team = teams.get(position);
        holder.countryView.setText(teams.get(position).getName());
        Glide.with(context)
                .load(teams.get(position).getCrestUrl())
                .into(holder.flagImageView);

        holder.view.setOnClickListener(v ->
                Timber.d("Team %s clicked", holder.team)
        );
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    class TeamsViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final TextView countryView;
        final ImageView flagImageView;
        Team team;

        TeamsViewHolder(View view) {
            super(view);
            this.view = view;
            countryView = view.findViewById(R.id.country);
            flagImageView = view.findViewById(R.id.flag);
        }
    }

}
