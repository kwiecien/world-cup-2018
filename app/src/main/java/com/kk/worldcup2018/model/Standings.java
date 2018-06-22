package com.kk.worldcup2018.model;

import lombok.Data;

@Data
public class Standings {
    private final int rank;
    private final String team;
    private final int teamId;
    private final int playedGames;
    private final int points;
    private final int goals;
    private final int goalsAgainst;
    private final int goalsDifference;
}
