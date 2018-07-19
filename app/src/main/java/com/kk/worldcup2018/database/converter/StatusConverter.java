package com.kk.worldcup2018.database.converter;

import android.arch.persistence.room.TypeConverter;

import static com.kk.worldcup2018.model.Fixture.Status;

public class StatusConverter {

    private StatusConverter() {
        throw new UnsupportedOperationException("Suppress default constructor for noninstantiability");
    }

    @TypeConverter
    public static Status toStatus(String status) {
        if (status.equals(Status.TIMED.name())) {
            return Status.TIMED;
        } else if (status.equals(Status.IN_PLAY.name())) {
            return Status.IN_PLAY;
        } else if (status.equals(Status.FINISHED.name())) {
            return Status.FINISHED;
        } else {
            throw new IllegalArgumentException("Could not recognize status");
        }
    }

    @TypeConverter
    public static String toString(Status status) {
        return status.name();
    }

}
