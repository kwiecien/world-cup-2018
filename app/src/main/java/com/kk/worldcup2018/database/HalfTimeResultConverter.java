package com.kk.worldcup2018.database;

import android.arch.persistence.room.TypeConverter;
import android.text.TextUtils;

import com.kk.worldcup2018.model.Result;

public class HalfTimeResultConverter {

    @TypeConverter
    public static Result toHalfTimeResult(String result) {
        String[] halfTimeGoals = TextUtils.split(result, ":");
        return halfTimeGoals.length > 0
                ? new Result(Integer.parseInt(halfTimeGoals[0]), Integer.parseInt(halfTimeGoals[1]), null)
                : new Result(0, 0, null);
    }

    @TypeConverter
    public static String toHalfTimeString(Result result) {
        return result != null ? result.getGoalsHomeTeam() + ":" + result.getGoalsAwayTeam() : "";
    }

}
