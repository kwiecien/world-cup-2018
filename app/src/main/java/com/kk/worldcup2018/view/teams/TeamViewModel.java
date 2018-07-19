package com.kk.worldcup2018.view.teams;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.kk.worldcup2018.database.AppDatabase;
import com.kk.worldcup2018.model.Team;

class TeamViewModel extends ViewModel {

    private final LiveData<Team> team;

    public TeamViewModel(int teamId, AppDatabase appDatabase) {
        team = appDatabase.teamDao().findTeamById(teamId);
    }

    public LiveData<Team> getTeam() {
        return team;
    }

}
