package com.kk.worldcup2018.database.converter;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;
import java.util.Optional;

public class DateConverter {

    private DateConverter() {
        throw new UnsupportedOperationException("Suppress default constructor for noninstantiability");
    }

    @TypeConverter
    public static Date toDate(Long timestamp) {
        return Optional.ofNullable(timestamp)
                .map(Date::new)
                .orElse(null);
    }

    @TypeConverter
    public static Long toTimestamp(Date date) {
        return Optional.ofNullable(date)
                .map(Date::getTime)
                .orElse(null);
    }

}
