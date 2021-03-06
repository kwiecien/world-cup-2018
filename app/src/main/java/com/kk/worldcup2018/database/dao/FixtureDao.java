package com.kk.worldcup2018.database.dao;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.kk.worldcup2018.model.Fixture;

import java.util.List;

@Dao
public interface FixtureDao {

    @Query("SELECT * FROM fixture")
    LiveData<List<Fixture>> findFixtures();

    @Insert
    void insertFixtures(List<Fixture> fixtures);
}
