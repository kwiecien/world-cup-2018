package com.kk.worldcup2018.dagger;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.kk.worldcup2018.BuildConfig;
import com.kk.worldcup2018.R;

public class WorldCupApplication extends Application {

    private static GoogleAnalytics googleAnalytics;
    private static Tracker tracker;

    @Override
    public void onCreate() {
        super.onCreate();
        googleAnalytics = GoogleAnalytics.getInstance(this);
        if (BuildConfig.DEBUG_MODE) {
            Stetho.initializeWithDefaults(this);
        }
    }

    public synchronized Tracker getTracker() {
        if (tracker == null) {
            tracker = googleAnalytics.newTracker(R.xml.global_tracker);
        }
        return tracker;
    }

}
