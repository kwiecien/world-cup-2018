package com.kk.worldcup2018.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.TypeConverters;
import android.content.Context;

import com.kk.worldcup2018.database.converter.DateConverter;
import com.kk.worldcup2018.database.converter.StatusConverter;
import com.kk.worldcup2018.database.dao.FixtureDao;
import com.kk.worldcup2018.database.dao.GroupDao;
import com.kk.worldcup2018.database.dao.PlayerDao;
import com.kk.worldcup2018.database.dao.StandingsDao;
import com.kk.worldcup2018.database.dao.TeamDao;
import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.model.Group;
import com.kk.worldcup2018.model.Player;
import com.kk.worldcup2018.model.Standings;
import com.kk.worldcup2018.model.Team;

import timber.log.Timber;

@Database(entities = {Team.class, Player.class, Fixture.class, Group.class, Standings.class},
        version = 4, exportSchema = false)
@TypeConverters({DateConverter.class, StatusConverter.class})
public abstract class AppDatabase extends RoomDatabase {
    private static final Object LOCK = new Object();
    private static final String DATABASE_NAME = "worldcup";
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context) {
        if (instance == null) {
            synchronized (LOCK) {
                Timber.d("Creating new database instance");
                instance = Room.databaseBuilder(context.getApplicationContext(),
                        AppDatabase.class, AppDatabase.DATABASE_NAME)
                        .build();
            }
        }
        Timber.d("Getting the database instance");
        return instance;
    }

    public abstract TeamDao teamDao();

    public abstract PlayerDao playerDao();

    public abstract FixtureDao fixtureDao();

    public abstract GroupDao groupDao();

    public abstract StandingsDao standingsDao();

}
