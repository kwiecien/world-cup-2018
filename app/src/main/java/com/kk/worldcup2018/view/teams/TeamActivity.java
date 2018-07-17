package com.kk.worldcup2018.view.teams;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Team;
import com.kk.worldcup2018.view.ActionBarActivity;

import org.parceler.Parcels;

public class TeamActivity extends ActionBarActivity {

    private static final String ARG_TEAM = "arg-team";

    public static Intent newIntent(@NonNull Context context, @NonNull Team team) {
        Intent intent = new Intent(context, TeamActivity.class);
        intent.putExtra(ARG_TEAM, Parcels.wrap(team));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);
        setupActionBar();

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            Team team = Parcels.unwrap(getIntent().getParcelableExtra(ARG_TEAM));
            fragment = TeamFragment.newInstance(team);
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
