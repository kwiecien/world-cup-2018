package com.kk.worldcup2018.database;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Query;

import com.kk.worldcup2018.model.Team;

@Dao
public interface TeamDao {

    @Query("SELECT * FROM team WHERE teamId = :id")
    LiveData<Team> loadTeamById(int id);

}
