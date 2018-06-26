package com.kk.worldcup2018.view.groups;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.kk.worldcup2018.R;
import com.kk.worldcup2018.model.Group;
import com.kk.worldcup2018.model.Standings;

import org.parceler.Parcels;

import java.util.List;

public class GroupFragment extends Fragment {

    private static final String ARG_GROUP = "arg-group";
    private Group group;

    public GroupFragment() {
        /*
         * Mandatory empty constructor for the fragment manager to instantiate the
         * fragment (e.g. upon screen orientation changes).
         */
    }

    public static GroupFragment newInstance(@NonNull Group group) {
        GroupFragment groupFragment = new GroupFragment();
        Bundle bundle = new Bundle();
        bundle.putParcelable(ARG_GROUP, Parcels.wrap(group));
        groupFragment.setArguments(bundle);
        return groupFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null && getArguments().containsKey(ARG_GROUP)) {
            group = Parcels.unwrap(getArguments().getParcelable(ARG_GROUP));
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_group, container, false);
        fillGroupTable((TableLayout) view, group.getStandingsList());
        return view;
    }

    private void fillGroupTable(@NonNull TableLayout tableLayout, List<Standings> standings) {
        fillRow(tableLayout.findViewById(R.id.first), standings.get(0));
        fillRow(tableLayout.findViewById(R.id.second), standings.get(1));
        fillRow(tableLayout.findViewById(R.id.third), standings.get(2));
        fillRow(tableLayout.findViewById(R.id.fourth), standings.get(3));
    }

    private void fillRow(TableRow tableRow, Standings standings) {
        TextView teamTextView = tableRow.findViewById(R.id.team);
        TextView goalsPlusTextView = tableRow.findViewById(R.id.goals_plus);
        TextView goalsMinusTextView = tableRow.findViewById(R.id.goals_minus);
        TextView goalDifferenceTextView = tableRow.findViewById(R.id.goal_difference);
        TextView pointsTextView = tableRow.findViewById(R.id.points);
        teamTextView.setText(standings.getTeam());
        goalsPlusTextView.setText(String.valueOf(standings.getGoals()));
        goalsMinusTextView.setText(String.valueOf(standings.getGoalsAgainst()));
        goalDifferenceTextView.setText(String.valueOf(standings.getGoalDifference()));
        pointsTextView.setText(String.valueOf(standings.getPoints()));
    }

}
