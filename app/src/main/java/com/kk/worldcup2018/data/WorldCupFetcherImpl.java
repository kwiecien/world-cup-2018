package com.kk.worldcup2018.data;

import android.support.annotation.NonNull;

import com.kk.worldcup2018.data.response.FixturesResponse;
import com.kk.worldcup2018.data.response.PlayersResponse;
import com.kk.worldcup2018.data.response.StandingsResponse;
import com.kk.worldcup2018.data.response.TeamsResponse;
import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.model.Group;
import com.kk.worldcup2018.model.Player;
import com.kk.worldcup2018.model.Team;

import java.util.ArrayList;
import java.util.Optional;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;

public class WorldCupFetcherImpl implements WorldCupFetcher {

    private final FootballFetcher fetcher;

    @Inject
    public WorldCupFetcherImpl(FootballFetcher fetcher) {
        this.fetcher = fetcher;
    }

    @Override
    public void fetchTeams(ListUpdateCallback<Team> callback) {
        fetcher.getTeams().enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(@NonNull Call<TeamsResponse> call,
                                   @NonNull Response<TeamsResponse> response) {
                callback.update(
                        Optional.ofNullable(response.body())
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
    public void fetchPlayers(int teamId, ListUpdateCallback<Player> callback) {
        fetcher.getPlayers(teamId).enqueue(new Callback<PlayersResponse>() {
            @Override
            public void onResponse(@NonNull Call<PlayersResponse> call,
                                   @NonNull Response<PlayersResponse> response) {
                callback.update(Optional.ofNullable(response.body())
                        .map(PlayersResponse::getObjects)
                        .orElseGet(ArrayList::new));
            }

            @Override
            public void onFailure(@NonNull Call<PlayersResponse> call,
                                  @NonNull Throwable t) {
                Timber.e(t);
            }
        });
    }

    @Override
    public void fetchFixtures(ListUpdateCallback<Fixture> callback) {
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
    public void fetchGroups(ListUpdateCallback<Group> callback) {
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