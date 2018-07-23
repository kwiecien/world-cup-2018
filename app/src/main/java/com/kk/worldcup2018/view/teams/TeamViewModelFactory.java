package com.kk.worldcup2018.view.teams;

import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.support.annotation.NonNull;

import com.kk.worldcup2018.database.AppDatabase;

class TeamViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    private final AppDatabase appDatabase;
    private final String team;

    public TeamViewModelFactory(AppDatabase appDatabase, String team) {
        this.appDatabase = appDatabase;
        this.team = team;
    }

    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        //noinspection unchecked
        return (T) new TeamViewModel(team, appDatabase);
    }
}
