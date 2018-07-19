package com.kk.worldcup2018.data.response;

import android.support.annotation.NonNull;

import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.model.Player;
import com.kk.worldcup2018.model.Team;

import java.util.ArrayList;
import java.util.List;

abstract class Response<T> {
    final List<Team> teams = new ArrayList<>();
    final List<Fixture> fixtures = new ArrayList<>();
    final List<Player> players = new ArrayList<>();

    @NonNull
    public abstract List<T> getObjects();
}