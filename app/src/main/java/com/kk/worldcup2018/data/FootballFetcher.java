package com.kk.worldcup2018.data;

import com.kk.worldcup2018.data.response.FixturesResponse;
import com.kk.worldcup2018.data.response.StandingsResponse;
import com.kk.worldcup2018.data.response.TeamsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface FootballFetcher {

    @Headers("X-Auth-Token: " + ApiKeys.FOOTBALL_DATA_ORG)
    @GET("teams")
    Call<TeamsResponse> getTeams();

    @Headers("X-Auth-Token: " + ApiKeys.FOOTBALL_DATA_ORG)
    @GET("fixtures")
    Call<FixturesResponse> getFixtures();

    @Headers("X-Auth-Token: " + ApiKeys.FOOTBALL_DATA_ORG)
    @GET("leagueTable")
    Call<StandingsResponse> getGroups();

}
