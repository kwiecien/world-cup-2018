package com.kk.worldcup2018.model;

import org.parceler.Parcel;

@Parcel
public class Result {
    int goalsHomeTeam;
    int goalsAwayTeam;
    Result halfTime;

    public Result() {

    }

    public Result(int goalsHomeTeam, int goalsAwayTeam, Result halfTime) {
        this.goalsHomeTeam = goalsHomeTeam;
        this.goalsAwayTeam = goalsAwayTeam;
        this.halfTime = halfTime;
    }

    public int getGoalsHomeTeam() {
        return goalsHomeTeam;
    }

    public int getGoalsAwayTeam() {
        return goalsAwayTeam;
    }

    public Result getHalfTime() {
        return halfTime;
    }
}
