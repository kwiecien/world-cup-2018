package com.kk.worldcup2018.data.response;

class GroupResponse {
    private final String group;
    private final int rank;
    private final String team;
    private final int teamId;
    private final int playedGames;
    private final int points;
    private final int goals;
    private final int goalsAgainst;
    private final int goalDifference;

    public GroupResponse(String group, int rank, String team, int teamId, int playedGames, int points, int goals, int goalsAgainst, int goalDifference) {
        this.group = group;
        this.rank = rank;
        this.team = team;
        this.teamId = teamId;
        this.playedGames = playedGames;
        this.points = points;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
    }

    public String getGroup() {
        return group;
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