package com.kk.worldcup2018.model;

import org.parceler.Parcel;

@Parcel
public class Standings {
    int rank;
    String team;
    int teamId;
    int playedGames;
    int points;
    int goals;
    int goalsAgainst;
    int goalDifference;

    public Standings() {

    }

    public Standings(int rank, String team, int teamId, int playedGames, int points, int goals, int goalsAgainst, int goalDifference) {
        this.rank = rank;
        this.team = team;
        this.teamId = teamId;
        this.playedGames = playedGames;
        this.points = points;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
    }

    public int getRank() {
        return rank;
    }

    public String getTeam() {
        return team;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getPlayedGames() {
        return playedGames;
    }

    public int getPoints() {
        return points;
    }

    public int getGoals() {
        return goals;
    }

    public int getGoalsAgainst() {
        return goalsAgainst;
    }

    public int getGoalDifference() {
        return goalDifference;
    }
}
