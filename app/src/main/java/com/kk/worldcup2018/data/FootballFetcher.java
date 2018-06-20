package com.kk.worldcup2018.data;

import android.support.annotation.NonNull;

import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.model.Team;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FootballFetcher {

    @GET("teams")
    Call<TeamsResponse> getTeams();

    @GET("fixtures")
    Call<FixturesResponse> getFixtures();

    abstract class Response<T> {
        List<Team> teams = new ArrayList<>();
        List<Fixture> fixtures = new ArrayList<>();

        @NonNull
        public abstract List<T> getObjects();
    }

    class TeamsResponse extends Response<Team> {
        @Override
        @NonNull
        public List<Team> getObjects() {
            return teams;
        }
    }

    class FixturesResponse extends Response<Fixture> {
        @Override
        @NonNull
        public List<Fixture> getObjects() {
            return fixtures;
        }
    }

}
