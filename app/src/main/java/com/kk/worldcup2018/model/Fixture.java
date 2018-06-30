package com.kk.worldcup2018.model;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
public class Fixture {
    Date date;
    Status status;
    int matchday;
    String homeTeamName;
    String awayTeamName;
    Result result;

    public Fixture() {

    }

    public Fixture(Date date, Status status, int matchday, String homeTeamName, String awayTeamName, Result result) {
        this.date = date;
        this.status = status;
        this.matchday = matchday;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.result = result;
    }

    public Date getDate() {
        return date;
    }

    public Status getStatus() {
        return status;
    }

    public int getMatchday() {
        return matchday;
    }

    public String getHomeTeamName() {
        return homeTeamName;
    }

    public String getAwayTeamName() {
        return awayTeamName;
    }

    public Result getResult() {
        return result;
    }

    public enum Status {
        TIMED("TIMED"), IN_PLAY("IN PLAY"), FINISHED("FINISHED");
        private final String fixtureStatus;

        Status(String fixtureStatus) {
            this.fixtureStatus = fixtureStatus;
        }

        @Override
        public String toString() {
            return fixtureStatus;
        }
    }

}
