package com.kk.worldcup2018.data.response;

import android.support.annotation.NonNull;

import com.kk.worldcup2018.model.Fixture;

import java.util.List;

public class FixturesResponse extends Response<Fixture> {
    @Override
    @NonNull
    public List<Fixture> getObjects() {
        return fixtures;
    }
}