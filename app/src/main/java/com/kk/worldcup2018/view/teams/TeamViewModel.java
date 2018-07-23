package com.kk.worldcup2018.view.teams;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.kk.worldcup2018.database.AppDatabase;
import com.kk.worldcup2018.model.Player;

import java.util.List;

class TeamViewModel extends ViewModel {

    private final LiveData<List<Player>> players;

    TeamViewModel(String team, AppDatabase appDatabase) {
        players = appDatabase.playerDao().findPlayersForTeam(team);
    }

    public LiveData<List<Player>> getPlayers() {
        return players;
    }

}
