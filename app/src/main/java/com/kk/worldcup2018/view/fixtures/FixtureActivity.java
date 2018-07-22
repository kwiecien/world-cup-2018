package com.kk.worldcup2018.view.fixtures;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Fixture;
import com.kk.worldcup2018.view.ActionBarActivity;

import org.parceler.Parcels;

public class FixtureActivity extends ActionBarActivity {

    private static final String ARG_FIXTURE = "arg-fixture";

    public static Intent newIntent(@NonNull Context context, @NonNull Fixture fixture) {
        Intent intent = new Intent(context, FixtureActivity.class);
        intent.putExtra(ARG_FIXTURE, Parcels.wrap(fixture));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixture);
        setupActionBar();

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment == null) {
            Fixture fixture = Parcels.unwrap(getIntent().getParcelableExtra(ARG_FIXTURE));
            fragment = FixtureFragment.newInstance(fixture);
            fm.beginTransaction()
                    .add(R.id.fragment_container, fragment)
                    .commit();
        }
    }

}
