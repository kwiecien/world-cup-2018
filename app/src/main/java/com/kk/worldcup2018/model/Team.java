package com.kk.worldcup2018.model;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Team {
    int teamId;
    String name;
    String code;
    String crestUrl;
    List<Player> players;

    public Team() {
        // Necessary for Parcels
    }

    public Team(int teamId, String name, String code, String crestUrl, List<Player> players) {
        this.teamId = teamId;
        this.name = name;
        this.code = code;
        this.crestUrl = crestUrl;
        this.players = players;
    }

    public int getTeamId() {
        return teamId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

}
