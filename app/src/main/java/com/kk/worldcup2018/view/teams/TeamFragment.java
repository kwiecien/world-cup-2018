package com.kk.worldcup2018.view.teams;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.databinding.FragmentTeamBinding;
import com.kk.worldcup2018.model.Team;

import org.parceler.Parcels;

public class TeamFragment extends Fragment {

    private static final String ARG_TEAM = "arg-team";
    private Team team;

    public TeamFragment() {
        /*
         * Mandatory empty constructor for the fragment manager to instantiate the
         * fragment (e.g. upon screen orientation changes).
         */
    }

    public static TeamFragment newInstance(@NonNull Team team) {
        TeamFragment teamFragment = new TeamFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_TEAM, Parcels.wrap(team));
        teamFragment.setArguments(bundle);
        return teamFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_TEAM)) {
            team = Parcels.unwrap(getArguments().getParcelable(ARG_TEAM));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentTeamBinding binding = DataBindingUtil.inflate(inflater, R.layout.fragment_team, container, false);
        View view = binding.getRoot();
        binding.setTeam(team);
        return view;
    }

}
