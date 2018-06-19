package com.kk.worldcup2018.model;

import lombok.Data;

@Data
class Result {
    private final int goalsHomeTeam;
    private final int goalsAwayTeam;
    private final Halftime halftime;

    @Data
    class Halftime {
        private final Result result;
    }

}
