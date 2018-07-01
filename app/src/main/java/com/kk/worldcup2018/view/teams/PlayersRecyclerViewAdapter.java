package com.kk.worldcup2018.view.teams;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.model.Player;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link Fixture}
 */
public class PlayersRecyclerViewAdapter extends RecyclerView.Adapter<PlayersRecyclerViewAdapter.PlayerViewHolder> {

    private List<Player> players;

    PlayersRecyclerViewAdapter(List<Player> players) {
        this.players = players;
    }

    @NonNull
    @Override
    public PlayerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_player, parent, false);
        return new PlayerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final PlayerViewHolder holder, int position) {
        holder.player = players.get(position);
        holder.name.setText(players.get(position).getName());
        holder.position.setText(players.get(position).getPosition());
        holder.jerseyNumber.setText(String.valueOf(players.get(position).getJerseyNumber()));
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    class PlayerViewHolder extends RecyclerView.ViewHolder {
        final View view;
        final TextView name;
        final TextView position;
        final TextView jerseyNumber;
        Player player;

        PlayerViewHolder(View view) {
            super(view);
            this.view = view;
            name = view.findViewById(R.id.name);
            position = view.findViewById(R.id.position);
            jerseyNumber = view.findViewById(R.id.jersey_number);
        }

    }
}
