package com.kk.worldcup2018.view;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.transition.Slide;
import android.view.Gravity;
import android.view.MenuItem;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.view.fixtures.FixturesFragment;
import com.kk.worldcup2018.view.groups.GroupsFragment;
import com.kk.worldcup2018.view.teams.TeamsFragment;

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
    private BottomNavigationView navigation;

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
        setContentView(R.layout.activity_main);
        setUpBottomNavigation();
        if (savedInstanceState == null) {
            removeSelection(navigation);
            setWelcomeScreen();
        }
    }

    private void setUpBottomNavigation() {
        navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
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
