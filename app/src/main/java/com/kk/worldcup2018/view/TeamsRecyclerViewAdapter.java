package com.kk.worldcup2018.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Team;
import com.kk.worldcup2018.view.TeamsFragment.OnListFragmentInteractionListener;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem} and makes a call to the
 * specified {@link OnListFragmentInteractionListener}.
 * TODO: Replace the implementation with code for your data type.
 */
public class TeamsRecyclerViewAdapter extends RecyclerView.Adapter<TeamsRecyclerViewAdapter.TeamsViewHolder> {

    private final OnListFragmentInteractionListener mListener;
    private List<Team> teams;

    public TeamsRecyclerViewAdapter(List<Team> teams, OnListFragmentInteractionListener listener) {
        this.teams = teams;
        mListener = listener;
    }

    @Override
    public TeamsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_teams, parent, false);
        return new TeamsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final TeamsViewHolder holder, int position) {
        holder.mItem = teams.get(position);
        holder.mIdView.setText(teams.get(position).getName());
        holder.mContentView.setText(teams.get(position).getCode());

        holder.mView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (null != mListener) {
                    // Notify the active callbacks interface (the activity, if the
                    // fragment is attached to one) that an item has been selected.
                    //mListener.onListFragmentInteraction(holder.mItem);
                }
            }
        });
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
        public final TextView mIdView;
        public final TextView mContentView;
        public Team mItem;

        public TeamsViewHolder(View view) {
            super(view);
            mView = view;
            mIdView = view.findViewById(R.id.item_number);
            mContentView = view.findViewById(R.id.content);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mContentView.getText() + "'";
        }
    }
}
