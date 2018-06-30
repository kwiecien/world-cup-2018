package com.kk.worldcup2018.data;

import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.model.Group;
import com.kk.worldcup2018.model.Player;
import com.kk.worldcup2018.model.Team;

import java.util.List;

public interface WorldCupFetcher {

    void fetchTeams(ListUpdateCallback<Team> callback);

    void fetchPlayers(int teamId, ListUpdateCallback<Player> callback);

    void fetchFixtures(ListUpdateCallback<Fixture> callback);

    void fetchGroups(ListUpdateCallback<Group> callback);

    interface ListUpdateCallback<T> {
        void update(List<T> objects);
    }

}
