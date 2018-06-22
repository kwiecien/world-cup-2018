package com.kk.worldcup2018.data;

import com.kk.worldcup2018.data.response.FixturesResponse;
import com.kk.worldcup2018.data.response.StandingsResponse;
import com.kk.worldcup2018.data.response.TeamsResponse;

import retrofit2.Call;
import retrofit2.http.GET;

public interface FootballFetcher {

    @GET("teams")
    Call<TeamsResponse> getTeams();

    @GET("fixtures")
    Call<FixturesResponse> getFixtures();

    @GET("leagueTable")
    Call<StandingsResponse> getGroups();

}
