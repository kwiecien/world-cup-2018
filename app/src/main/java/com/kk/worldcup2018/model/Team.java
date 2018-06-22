package com.kk.worldcup2018.model;

import java.util.List;

import lombok.Data;

@Data
public class Team {
    private final int teamId;
    private final String name;
    private final String code;
    private final String crestUrl;
    private final List<Player> players;
}
