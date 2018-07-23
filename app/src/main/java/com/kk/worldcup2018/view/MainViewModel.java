package com.kk.worldcup2018.view;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.kk.worldcup2018.database.AppDatabase;
import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.model.Team;

import java.util.List;

public class MainViewModel extends AndroidViewModel {

    private LiveData<List<Team>> teams;
    private LiveData<List<Fixture>> fixtures;

    public MainViewModel(@NonNull Application application) {
        super(application);
        AppDatabase db = AppDatabase.getInstance(getApplication());
        teams = db.teamDao().findTeams();
        fixtures = db.fixtureDao().findFixtures();
    }

    public LiveData<List<Team>> getTeams() {
        return teams;
    }

    public LiveData<List<Fixture>> getFixtures() {
        return fixtures;
    }

}
