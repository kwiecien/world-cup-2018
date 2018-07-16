package com.kk.worldcup2018.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kk.worldcup2018.model.Team;

import java.util.List;

@Dao
public interface TeamDao {

    @Query("SELECT * FROM team WHERE teamId = :id")
    LiveData<Team> findTeamById(int id);

    @Query("SELECT * FROM team ORDER BY name DESC")
    List<Team> findTeams();

    @Insert
    void insertTeams(List<Team> teams);

}
