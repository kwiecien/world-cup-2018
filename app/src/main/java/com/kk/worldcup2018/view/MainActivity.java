package com.kk.worldcup2018.view;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;

import com.google.firebase.messaging.FirebaseMessaging;
import com.kk.worldcup2018.R;
import com.kk.worldcup2018.view.fixtures.FixturesFragment;
import com.kk.worldcup2018.view.groups.GroupsFragment;
import com.kk.worldcup2018.view.teams.TeamsFragment;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private final BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        restoreSelection(item);
        switch (item.getItemId()) {
            case R.id.navigation_teams:
                replaceFragmentWith(TeamsFragment.newInstance());
                return true;
            case R.id.navigation_fixtures:
                replaceFragmentWith(FixturesFragment.newInstance());
                return true;
            case R.id.navigation_groups:
                replaceFragmentWith(GroupsFragment.newInstance());
                return true;
            default:
        }
        return false;
    };

    private void restoreSelection(MenuItem item) {
        item.setCheckable(true);
    }

    private void replaceFragmentWith(Fragment newFragment) {
        prepareTransition(newFragment);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, newFragment)
                .commit();
    }

    private void prepareTransition(Fragment newFragment) {
        Slide slideEnter = new Slide();
        slideEnter.setSlideEdge(Gravity.BOTTOM);
        newFragment.setEnterTransition(slideEnter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpTimber();
        setContentView(R.layout.activity_main);
        setUpBottomNavigation();
        setUpFirebaseCloudMessaging();
        setWelcomeScreen();
    }

    private void setUpFirebaseCloudMessaging() {
        createNotificationChannel();
        FirebaseMessaging.getInstance().subscribeToTopic("wc2018");
    }

    private void setUpTimber() {
        Timber.plant(new Timber.DebugTree());
    }

    private void setUpBottomNavigation() {
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        removeSelection(navigation);
    }

    private void removeSelection(BottomNavigationView navigation) {
        for (int i = 0; i < navigation.getMenu().size(); i++) {
            navigation.getMenu().getItem(i).setCheckable(false);
            navigation.getMenu().getItem(i).setChecked(false);
        }
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

    private void setWelcomeScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, WelcomeFragment.newInstance())
                .commit();
    }

}
