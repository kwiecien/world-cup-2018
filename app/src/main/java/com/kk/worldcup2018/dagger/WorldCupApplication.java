package com.kk.worldcup2018.dagger;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.kk.worldcup2018.R;

public class WorldCupApplication extends Application {

    private static GoogleAnalytics googleAnalytics;
    private static Tracker tracker;
    private WorldCupComponent worldCupComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
        googleAnalytics = GoogleAnalytics.getInstance(this);
        worldCupComponent = createWorldCupComponent();
    }

    public WorldCupComponent getWorldCupComponent() {
        return worldCupComponent;
    }

    public synchronized Tracker getTracker() {
        if (tracker == null) {
            tracker = googleAnalytics.newTracker(R.xml.global_tracker);
        }
        return tracker;
    }

    private WorldCupComponent createWorldCupComponent() {
        return DaggerWorldCupComponent
                .builder()
                .build();
    }
}
