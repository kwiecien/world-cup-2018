package com.kk.worldcup2018.model;

import lombok.Data;

@Data
public class Result {
    private final int goalsHomeTeam;
    private final int goalsAwayTeam;
    private final Result halfTime;
}
