package com.kk.worldcup2018.data.response;

import android.support.annotation.NonNull;

import com.kk.worldcup2018.model.Player;

import java.util.List;

public class PlayersResponse extends Response<Player> {
    @Override
    @NonNull
    public List<Player> getObjects() {
        return players;
    }
}