package com.kk.worldcup2018.data.response;

import android.support.annotation.NonNull;

import com.kk.worldcup2018.model.Team;

import java.util.List;

public class TeamsResponse extends Response<Team> {
    @Override
    @NonNull
    public List<Team> getObjects() {
        return teams;
    }
}