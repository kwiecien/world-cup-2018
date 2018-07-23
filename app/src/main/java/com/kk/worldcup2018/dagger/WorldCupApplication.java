package com.kk.worldcup2018.dagger;

import android.app.Application;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;

import com.facebook.stetho.Stetho;
import com.google.android.gms.analytics.GoogleAnalytics;
import com.google.android.gms.analytics.Tracker;
import com.google.firebase.messaging.FirebaseMessaging;
import com.kk.worldcup2018.BuildConfig;
import com.kk.worldcup2018.R;

import timber.log.Timber;

public class WorldCupApplication extends Application {

    private static GoogleAnalytics googleAnalytics;
    private static Tracker tracker;

    @Override
    public void onCreate() {
        super.onCreate();
        setUpGoogleAnalytics();
        setUpFirebaseCloudMessaging();
        if (BuildConfig.DEBUG_MODE) {
            setUpStetho();
            setUpTimber();
        }
    }

    private void setUpGoogleAnalytics() {
        googleAnalytics = GoogleAnalytics.getInstance(this);
    }

    private void setUpFirebaseCloudMessaging() {
        createNotificationChannel();
        FirebaseMessaging.getInstance().subscribeToTopic("wc2018");
    }

    private void setUpStetho() {
        Stetho.initializeWithDefaults(this);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelName = "World Cup 2018";
            String channelDescription = "Channel for World Cup 2018 notifications";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel notificationChannel =
                    new NotificationChannel(getString(R.string.default_notification_channel_id), channelName, importance);
            notificationChannel.setDescription(channelDescription);
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(notificationChannel);
        }
    }

    private void setUpTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    public synchronized Tracker getTracker() {
        if (tracker == null) {
            tracker = googleAnalytics.newTracker(R.xml.global_tracker);
        }
        return tracker;
    }

}
