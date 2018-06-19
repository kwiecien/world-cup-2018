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

    class TeamsResponse {
        private List<Team> teams = new ArrayList<>();

        @NonNull
        public List<Team> getTeams() {
            return teams;
        }
    }

    class FixturesResponse {
        private List<Fixture> fixtures = new ArrayList<>();

        @NonNull
        public List<Fixture> getFixtures() {
            return fixtures;
        }
    }

}
