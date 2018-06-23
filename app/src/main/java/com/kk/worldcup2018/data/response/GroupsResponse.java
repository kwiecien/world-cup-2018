package com.kk.worldcup2018.data.response;

import android.support.annotation.NonNull;

import com.kk.worldcup2018.model.Group;
import com.kk.worldcup2018.model.Standings;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GroupsResponse {
    private final List<GroupResponse> A;
    private final List<GroupResponse> B;
    private final List<GroupResponse> C;
    private final List<GroupResponse> D;
    private final List<GroupResponse> E;
    private final List<GroupResponse> F;
    private final List<GroupResponse> G;
    private final List<GroupResponse> H;

    public GroupsResponse(List<GroupResponse> a, List<GroupResponse> b, List<GroupResponse> c, List<GroupResponse> d,
                          List<GroupResponse> e, List<GroupResponse> f, List<GroupResponse> g, List<GroupResponse> h) {
        A = a;
        B = b;
        C = c;
        D = d;
        E = e;
        F = f;
        G = g;
        H = h;
    }

    @NonNull
    public List<Group> getGroups() {
        return Arrays.asList(toGroup(A), toGroup(B), toGroup(C), toGroup(D),
                toGroup(E), toGroup(F), toGroup(G), toGroup(H));
    }

    private Group toGroup(List<GroupResponse> groupResponses) {
        List<Standings> standings = new ArrayList<>();
        String groupLetter = groupResponses.get(0).getGroup();
        for (GroupResponse groupResponse : groupResponses) {
            standings.add(new Standings(groupResponse.getRank(), groupResponse.getTeam(), groupResponse.getTeamId(),
                    groupResponse.getPlayedGames(), groupResponse.getPoints(),
                    groupResponse.getGoals(), groupResponse.getGoalsAgainst(), groupResponse.getGoalDifference()));
        }
        return new Group(groupLetter, standings);
    }

}