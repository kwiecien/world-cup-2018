package com.kk.worldcup2018.data;

import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.model.Group;
import com.kk.worldcup2018.model.Team;

import java.util.List;

public interface WorldCupFetcher {

    void fetchTeams(UpdateCallback<Team> callback);

    void fetchFixtures(UpdateCallback<Fixture> callback);

    void fetchGroups(UpdateCallback<Group> callback);

    interface UpdateCallback<T> {
        void update(List<T> objects);
    }

}
