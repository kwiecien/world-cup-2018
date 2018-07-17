package com.kk.worldcup2018.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import com.kk.worldcup2018.R;

import java.util.Optional;

public abstract class ActionBarActivity extends AppCompatActivity {

    protected void setupActionBar() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Optional.ofNullable(getSupportActionBar())
                .ifPresent(ab -> ab.setDisplayHomeAsUpEnabled(true));
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
