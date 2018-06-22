package com.kk.worldcup2018.data.response;

import lombok.Data;

@Data
class GroupResponse {
    private final String group;
    private final int rank;
    private final String team;
    private final int teamId;
    private final int playedGames;
    private final int points;
    private final int goals;
    private final int goalsAgainst;
    private final int goalsDifference;
}