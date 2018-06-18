package com.kk.worldcup2018.data;

import android.support.annotation.NonNull;

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
    public void fetchTeams(UpdateCallback callback) {
        fetcher.getTeams().enqueue(new Callback<FootballFetcher.TeamsResponse>() {
            @Override
            public void onResponse(@NonNull Call<FootballFetcher.TeamsResponse> call, @NonNull Response<FootballFetcher.TeamsResponse> response) {
                callback.update(Optional.ofNullable(response.body())
                        .map(FootballFetcher.TeamsResponse::getTeams)
                        .orElseGet(ArrayList::new));
            }

            @Override
            public void onFailure(Call<FootballFetcher.TeamsResponse> call, Throwable t) {
                Timber.e(t);
            }
        });
    }

}