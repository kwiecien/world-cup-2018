package com.kk.worldcup2018.model;

import java.util.Date;

import lombok.Data;

@Data
public class Fixture {
    private final Date date;
    private final Status status;
    private final int matchday;
    private final String homeTeamName;
    private final String awayTeamName;
    private final Result result;

    enum Status {
        TIMED, IN_PLAY, FINISHED
    }

}
