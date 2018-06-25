package com.kk.worldcup2018.view;

import android.support.annotation.NonNull;
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

    private List<Group> groups;

    public GroupsRecyclerViewAdapter(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public GroupsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_group, parent, false);
        return new GroupsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GroupsViewHolder holder, int position) {
        holder.group = groups.get(position);
        holder.idView.setText(groups.get(position).getLetter());
        holder.contentView.setText(groups.get(position).getStandingsList().toString());

        holder.mView.setOnClickListener(v ->
                Timber.d("Fixture %s clicked", holder.group.getLetter())
        );
    }

    public List<Group> getGroups() {
        return groups;
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    @Override
    public int getItemCount() {
        return groups.size();
    }

    public class GroupsViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView idView;
        public final TextView contentView;
        public Group group;

        public GroupsViewHolder(View view) {
            super(view);
            mView = view;
            idView = view.findViewById(R.id.country);
            contentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + contentView.getText() + "'";
        }
    }
}
