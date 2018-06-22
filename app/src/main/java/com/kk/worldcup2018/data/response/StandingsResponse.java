package com.kk.worldcup2018.data.response;

import com.kk.worldcup2018.model.Group;

import java.util.List;

public class StandingsResponse {
    private final GroupsResponse standings;

    public StandingsResponse(GroupsResponse standings) {
        this.standings = standings;
    }

    public List<Group> getGroups() {
        return standings.getGroups();
    }

}