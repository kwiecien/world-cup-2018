package com.kk.worldcup2018.view;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;

import com.kk.worldcup2018.R;

import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = item -> {
        switch (item.getItemId()) {
            case R.id.navigation_teams:
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.main_fragment, TeamsFragment.newInstance(1))
                        .commit();
                return true;
            case R.id.navigation_fixtures:
                return true;
            case R.id.navigation_groups:
                return true;
            default:
        }
        return false;
    };

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
    }

    private void setWelcomeScreen() {
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main_fragment, WelcomeFragment.newInstance())
                .commit();
    }

}
