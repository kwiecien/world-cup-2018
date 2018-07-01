package com.kk.worldcup2018.dagger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.kk.worldcup2018.data.FootballFetcher;
import com.kk.worldcup2018.data.TeamLinksDeserializer;
import com.kk.worldcup2018.data.WorldCupFetcher;
import com.kk.worldcup2018.data.WorldCupFetcherImpl;
import com.kk.worldcup2018.data.response.TeamsResponse;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Module
class WorldCupModule {

    @Provides
    @Singleton
    WorldCupFetcher provideWorldCupFetcher(FootballFetcher footballFetcher) {
        return new WorldCupFetcherImpl(footballFetcher);
    }

    @Provides
    @Singleton
    FootballFetcher provideFootballFetcher(Retrofit retrofit) {
        return retrofit.create(FootballFetcher.class);
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(String apiEndpoint, GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(apiEndpoint)
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

    @Provides
    @Singleton
    String provideWorldCupApiEndpoint() {
        return "http://api.football-data.org/v1/";
    }

    @Provides
    @Singleton
    Gson provideGson() {
        return new GsonBuilder()
                .registerTypeAdapter(TeamsResponse.TeamId.class, new TeamLinksDeserializer())
                .create();
    }

    @Provides
    @Singleton
    GsonConverterFactory provideGsonConverterFactory(Gson gson) {
        return GsonConverterFactory.create(gson);
    }

}
