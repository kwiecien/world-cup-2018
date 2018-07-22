package com.kk.worldcup2018.view.groups;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.transition.Explode;
import android.support.transition.Transition;
import android.support.transition.TransitionManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Group;

import java.util.List;

import timber.log.Timber;

/**
 * {@link RecyclerView.Adapter} that can display a {@link com.kk.worldcup2018.model.Group}
 */
public class GroupsRecyclerViewAdapter extends RecyclerView.Adapter<GroupsRecyclerViewAdapter.GroupsViewHolder> {

    private final Context context;
    private ViewGroup recyclerView;
    private List<Group> groups;

    GroupsRecyclerViewAdapter(Context context, List<Group> groups) {
        this.context = context;
        this.groups = groups;
    }

    @NonNull
    @Override
    public GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        recyclerView = parent;
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_group, parent, false);
        return new GroupsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final GroupsViewHolder holder, int position) {
        holder.group = groups.get(position);
        holder.groupView.setText(String.format("Group %s", groups.get(position).getLetter()));

        holder.view.setOnClickListener(v -> {
            Timber.d("Fixture %s clicked", holder.group.getLetter());
            prepareTransition();
            context.startActivity(GroupActivity.newIntent(context, holder.group),
                    ActivityOptions.makeSceneTransitionAnimation((Activity) context).toBundle());
        });
    }

    private void prepareTransition() {
        Transition transition = new Explode();
        transition.setDuration(1000);
        TransitionManager.beginDelayedTransition(recyclerView, transition);
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    class GroupsViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final TextView groupView;
        Group group;

        GroupsViewHolder(View view) {
            super(view);
            this.view = view;
            groupView = view.findViewById(R.id.group);
        }
    }
}
