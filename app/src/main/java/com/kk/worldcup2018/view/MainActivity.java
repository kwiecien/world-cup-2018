package com.kk.worldcup2018.view;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import com.kk.worldcup2018.R;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        restoreSelection(item);
        switch (item.getItemId()) {
            case R.id.navigation_teams:
                replaceFragmentWith(TeamsFragment.newInstance(1));
                return true;
            case R.id.navigation_fixtures:
                replaceFragmentWith(FixturesFragment.newInstance());
                return true;
            case R.id.navigation_groups:
                return true;
            default:
        }
        return false;
    };

    private void restoreSelection(MenuItem item) {
        item.setCheckable(true);
    }

    private void replaceFragmentWith(Fragment newFragment) {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, newFragment)
                .commit();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setUpTimber();
        setContentView(R.layout.activity_main);
        setUpBottomNavigation();
        setWelcomeScreen();
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

    private void setWelcomeScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, WelcomeFragment.newInstance())
                .commit();
    }

}
