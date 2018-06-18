package com.kk.worldcup2018.data;

import com.kk.worldcup2018.model.Team;

import java.util.List;

public interface WorldCupFetcher {

    void fetchTeams(UpdateCallback callback);

    interface UpdateCallback {
        void update(List<Team> teams);
    }

}
