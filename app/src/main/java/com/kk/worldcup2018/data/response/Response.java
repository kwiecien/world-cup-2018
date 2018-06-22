package com.kk.worldcup2018.data.response;

import android.support.annotation.NonNull;

import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.model.Team;

import java.util.ArrayList;
import java.util.List;

abstract class Response<T> {
    List<Team> teams = new ArrayList<>();
    List<Fixture> fixtures = new ArrayList<>();

    @NonNull
    public abstract List<T> getObjects();
}