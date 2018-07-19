package com.kk.worldcup2018.view.support;

import android.app.Activity;

import com.google.android.gms.analytics.HitBuilders;
import com.google.android.gms.analytics.Tracker;
import com.kk.worldcup2018.dagger.WorldCupApplication;

public class GoogleAnalyticsUtils {

    private GoogleAnalyticsUtils() {
        throw new UnsupportedOperationException("Suppress default constructor for noninstantiability");
    }

    public static Tracker initializeTracker(Activity activity) {
        WorldCupApplication application = (WorldCupApplication) activity.getApplication();
        return application.getTracker();
    }

    public static void trackScreen(Tracker tracker, String tag) {
        tracker.setScreenName(tag);
        tracker.send(new HitBuilders.ScreenViewBuilder().build());
    }

}
