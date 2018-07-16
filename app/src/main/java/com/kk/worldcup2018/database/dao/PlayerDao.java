package com.kk.worldcup2018.database.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kk.worldcup2018.model.Player;

import java.util.List;

@Dao
public interface PlayerDao {

    @Insert
    void insertPlayers(List<Player> players);

    @Query("SELECT * FROM player WHERE teamName = :team")
    List<Player> findPlayersForTeam(String team);

}
