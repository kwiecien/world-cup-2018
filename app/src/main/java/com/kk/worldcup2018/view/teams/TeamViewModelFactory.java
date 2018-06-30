package com.kk.worldcup2018.view.teams;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.kk.worldcup2018.database.AppDatabase;

public class TeamViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase appDatabase;
    private final int teamId;

    public TeamViewModelFactory(AppDatabase appDatabase, int teamId) {
        this.appDatabase = appDatabase;
        this.teamId = teamId;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new TeamViewModel(teamId, appDatabase);
    }
}
