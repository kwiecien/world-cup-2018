package com.kk.worldcup2018.data;

import android.support.annotation.NonNull;

import com.kk.worldcup2018.data.response.FixturesResponse;
import com.kk.worldcup2018.data.response.StandingsResponse;
import com.kk.worldcup2018.data.response.TeamsResponse;
import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.model.Group;
import com.kk.worldcup2018.model.Team;

import java.util.ArrayList;
import java.util.Optional;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class WorldCupFetcherImpl implements WorldCupFetcher {

    private static final String WORLD_CUP_API_ENDPOINT = "http://api.football-data.org/v1/competitions/467/";
    private final FootballFetcher fetcher = new Retrofit.Builder()
            .baseUrl(WORLD_CUP_API_ENDPOINT)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(FootballFetcher.class);

    @Override
    public void fetchTeams(UpdateCallback<Team> callback) {
        fetcher.getTeams().enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TeamsResponse> call,
                                   @NonNull Response<TeamsResponse> response) {
                callback.update(Optional.ofNullable(response.body())
                        .map(TeamsResponse::getObjects)
                        .orElseGet(ArrayList::new));
            }

            @Override
            public void onFailure(@NonNull Call<TeamsResponse> call,
                                  @NonNull Throwable t) {
                Timber.e(t);
            }
        });
    }

    @Override
    public void fetchFixtures(UpdateCallback<Fixture> callback) {
        fetcher.getFixtures().enqueue(new Callback<FixturesResponse>() {
            @Override
            public void onResponse(@NonNull Call<FixturesResponse> call,
                                   @NonNull Response<FixturesResponse> response) {
                callback.update(Optional.ofNullable(response.body())
                        .map(FixturesResponse::getObjects)
                        .orElseGet(ArrayList::new));
            }

            @Override
            public void onFailure(@NonNull Call<FixturesResponse> call,
                                  @NonNull Throwable t) {
                Timber.e(t);
            }
        });
    }

    @Override
    public void fetchGroups(UpdateCallback<Group> callback) {
        fetcher.getGroups().enqueue(new Callback<StandingsResponse>() {
            @Override
            public void onResponse(@NonNull Call<StandingsResponse> call,
                                   @NonNull Response<StandingsResponse> response) {
                callback.update(Optional.ofNullable(response.body())
                        .map(StandingsResponse::getGroups)
                        .orElseGet(ArrayList::new));
            }

            @Override
            public void onFailure(@NonNull Call<StandingsResponse> call,
                                  @NonNull Throwable t) {
                Timber.e(t);
            }
        });
    }

}