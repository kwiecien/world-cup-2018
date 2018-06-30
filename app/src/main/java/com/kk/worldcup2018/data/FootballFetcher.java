package com.kk.worldcup2018.data;

import com.kk.worldcup2018.data.response.FixturesResponse;
import com.kk.worldcup2018.data.response.PlayersResponse;
import com.kk.worldcup2018.data.response.StandingsResponse;
import com.kk.worldcup2018.data.response.TeamsResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface FootballFetcher {

    @Headers("X-Auth-Token: " + ApiKeys.FOOTBALL_DATA_ORG)
    @GET("competitions/467/teams")
    Call<TeamsResponse> getTeams();

    @Headers("X-Auth-Token: " + ApiKeys.FOOTBALL_DATA_ORG)
    @GET("teams/{id}/players")
    Call<PlayersResponse> getPlayers(@Path("id") int teamId);

    @Headers("X-Auth-Token: " + ApiKeys.FOOTBALL_DATA_ORG)
    @GET("competitions/467/fixtures")
    Call<FixturesResponse> getFixtures();

    @Headers("X-Auth-Token: " + ApiKeys.FOOTBALL_DATA_ORG)
    @GET("competitions/467/leagueTable")
    Call<StandingsResponse> getGroups();

}
