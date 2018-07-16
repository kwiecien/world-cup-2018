package com.kk.worldcup2018.model;

import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.TypeConverters;

import com.kk.worldcup2018.database.HalfTimeResultConverter;

import org.parceler.Parcel;

@Parcel
public class Result {
    int goalsHomeTeam;
    int goalsAwayTeam;
    @TypeConverters(HalfTimeResultConverter.class)
    Result halfTime;

    @Ignore
    public Result() {
        // Necessary for Parcels
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
