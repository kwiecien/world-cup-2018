package com.kk.worldcup2018.model;

import java.util.List;

import lombok.Data;

@Data
public class Team {
    private final String name;
    private final String code;
    private final String crestUrl;
    private final List<Player> players;

    public Team(String name, String code, String crestUrl, List<Player> players) {
        this.name = name;
        this.code = code;
        this.crestUrl = crestUrl;
        this.players = players;
    }

}
