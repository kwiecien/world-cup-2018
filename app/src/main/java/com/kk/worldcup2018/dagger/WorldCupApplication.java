package com.kk.worldcup2018.dagger;

import android.app.Application;

public class WorldCupApplication extends Application {

    private WorldCupComponent worldCupComponent;

    @Override
    public void onCreate() {
        super.onCreate();
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
