package com.kk.worldcup2018.view.fixtures;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Fixture;

import org.parceler.Parcels;

public class FixtureFragment extends Fragment {

    private static final String ARG_FIXTURE = "arg-fixture";
    private Fixture fixture;

    public FixtureFragment() {
        /*
         * Mandatory empty constructor for the fragment manager to instantiate the
         * fragment (e.g. upon screen orientation changes).
         */
    }

    public static FixtureFragment newInstance(@NonNull Fixture fixture) {
        FixtureFragment groupFragment = new FixtureFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_FIXTURE, Parcels.wrap(fixture));
        groupFragment.setArguments(bundle);
        return groupFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_FIXTURE)) {
            fixture = Parcels.unwrap(getArguments().getParcelable(ARG_FIXTURE));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fixture, container, false);
        return view;
    }

}
