package com.kk.worldcup2018.view.fixtures;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Fixture;

import org.parceler.Parcels;

public class FixtureActivity extends AppCompatActivity {

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


        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

    private void setupActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
