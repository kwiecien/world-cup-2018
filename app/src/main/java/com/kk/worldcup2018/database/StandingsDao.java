package com.kk.worldcup2018.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kk.worldcup2018.model.Standings;

import java.util.List;

@Dao
public interface StandingsDao {

    @Query("SELECT * FROM standings")
    List<Standings> findStandings();

    @Insert
    void insertStandings(List<Standings> standings);
}
