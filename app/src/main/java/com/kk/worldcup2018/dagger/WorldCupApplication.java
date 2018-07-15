package com.kk.worldcup2018.dagger;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class WorldCupApplication extends Application {

    private WorldCupComponent worldCupComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        worldCupComponent = createWorldCupComponent();
    }

    public WorldCupComponent getWorldCupComponent() {
        return worldCupComponent;
    }

    private WorldCupComponent createWorldCupComponent() {
        return DaggerWorldCupComponent
                .builder()
                .build();
    }
}
