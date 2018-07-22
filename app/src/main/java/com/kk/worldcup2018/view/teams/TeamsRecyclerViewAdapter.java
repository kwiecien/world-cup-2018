package com.kk.worldcup2018.view.teams;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.graphics.drawable.PictureDrawable;
import android.support.annotation.NonNull;
import android.support.transition.Explode;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Team;
import com.kk.worldcup2018.svg.GlideApp;
import com.kk.worldcup2018.svg.SvgSoftwareLayerSetter;

import java.util.List;

import timber.log.Timber;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Team}
 */
public class TeamsRecyclerViewAdapter extends RecyclerView.Adapter<TeamsRecyclerViewAdapter.TeamsViewHolder> {

    private final Context context;
    private ViewGroup recyclerView;
    private List<Team> teams;

    TeamsRecyclerViewAdapter(Context context, List<Team> teams) {
        this.context = context;
        this.teams = teams;
    }

    @NonNull
    @Override
    public TeamsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        recyclerView = parent;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_team, parent, false);
        return new TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final TeamsViewHolder holder, int position) {
        holder.team = teams.get(position);
        holder.countryView.setText(teams.get(position).getName());
        GlideApp.with(context)
                .as(PictureDrawable.class)
                .error(R.drawable.flag_icon)
                .transition(withCrossFade())
                .listener(new SvgSoftwareLayerSetter())
                .load(teams.get(position).getCrestUrl())
                .into(holder.flagImageView);

        holder.view.setOnClickListener(v -> {
            Timber.d("Team %s clicked", holder.team);
            prepareTransition();
            context.startActivity(TeamActivity.newIntent(context, holder.team),
                    ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
        });
    }

    private void prepareTransition() {
        Transition transition = new Explode();
        transition.setDuration(1000);
        TransitionManager.beginDelayedTransition(recyclerView, transition);
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
