package com.kk.worldcup2018.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kk.worldcup2018.model.Group;

import java.util.List;

@Dao
public interface GroupDao {

    @Query("SELECT * FROM `group`")
    List<Group> findGroups();

    @Insert
    void insertGroups(List<Group> groups);

}
