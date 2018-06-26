package com.kk.worldcup2018.view.groups;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Group;

import org.parceler.Parcels;

public class GroupActivity extends AppCompatActivity {


    private static final String ARG_GROUP = "arg-group";

    public static Intent newIntent(@NonNull Context context, @NonNull Group group) {
        Intent intent = new Intent(context, GroupActivity.class);
        intent.putExtra(ARG_GROUP, Parcels.wrap(group));
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_group);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Group group = Parcels.unwrap(getIntent().getParcelableExtra(ARG_GROUP));
        GroupFragment groupFragment = GroupFragment.newInstance(group);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.table_group, groupFragment)
                .commit();

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show());
    }

}
