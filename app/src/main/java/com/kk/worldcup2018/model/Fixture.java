package com.kk.worldcup2018.model;

import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import org.parceler.Parcel;

import java.util.Date;

@Parcel
@Entity
public class Fixture {
    @PrimaryKey(autoGenerate = true)
    int id;
    Date date;
    Status status;
    int matchday;
    String homeTeamName;
    String awayTeamName;
    @Embedded
    Result result;

    @Ignore
    public Fixture() {
        // Necessary for Parcels
    }

    public Fixture(int id, Date date, Status status, int matchday, String homeTeamName, String awayTeamName, Result result) {
        this.id = id;
        this.date = date;
        this.status = status;
        this.matchday = matchday;
        this.homeTeamName = homeTeamName;
        this.awayTeamName = awayTeamName;
        this.result = result;
    }

    public int getId() {
        return id;
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
        TIMED("TIMED"),
        IN_PLAY("IN PLAY"),
        FINISHED("FINISHED");

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
