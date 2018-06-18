package com.kk.worldcup2018.dagger;

import com.kk.worldcup2018.data.WorldCupFetcher;
import com.kk.worldcup2018.data.WorldCupFetcherImpl;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
class WorldCupModule {

    @Provides
    @Singleton
    WorldCupFetcher provideWorldCupFetcher() {
        return new WorldCupFetcherImpl();
    }

}
