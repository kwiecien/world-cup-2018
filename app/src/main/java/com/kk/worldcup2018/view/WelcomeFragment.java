package com.kk.worldcup2018.view;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.analytics.Tracker;
import com.kk.worldcup2018.R;
import com.kk.worldcup2018.view.support.GoogleAnalyticsUtils;

public class WelcomeFragment extends Fragment {

    private static final String TAG = WelcomeFragment.class.getSimpleName();
    private Tracker tracker;

    public static WelcomeFragment newInstance() {
        return new WelcomeFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tracker = GoogleAnalyticsUtils.initializeTracker(getActivity());
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_welcome, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        GoogleAnalyticsUtils.trackScreen(tracker, TAG);
    }

}

