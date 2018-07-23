package com.kk.worldcup2018.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kk.worldcup2018.model.Standings;

import java.util.List;

@Dao
public interface StandingsDao {

    @Insert
    void insertStandings(List<Standings> standings);

    @Query("SELECT * FROM standings WHERE groupLetter = :letter")
    LiveData<List<Standings>> findStandingsForGroup(String letter);

    @Query("SELECT * FROM standings WHERE team = :team")
    LiveData<Standings> findStandingsForTeam(String team);

}
