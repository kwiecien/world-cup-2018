package com.kk.worldcup2018.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import org.parceler.Parcel;

@Parcel
@Entity(foreignKeys = @ForeignKey(entity = Group.class,
        parentColumns = "letter",
        childColumns = "groupLetter"),
        indices = {@Index("id"), @Index("groupLetter")})
public class Standings {
    @PrimaryKey(autoGenerate = true)
    int id;
    int rank;
    String team;
    int teamId;
    int playedGames;
    int points;
    int goals;
    int goalsAgainst;
    int goalDifference;
    String groupLetter;

    @Ignore
    public Standings() {
        // Necessary for Parcels
    }

    @Ignore
    public Standings(int rank, String team, int teamId, int playedGames, int points, int goals, int goalsAgainst, int goalDifference, String groupLetter) {
        this.rank = rank;
        this.team = team;
        this.teamId = teamId;
        this.playedGames = playedGames;
        this.points = points;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.groupLetter = groupLetter;
    }

    public Standings(int id, int rank, String team, int teamId, int playedGames, int points, int goals, int goalsAgainst, int goalDifference, String groupLetter) {
        this.id = id;
        this.rank = rank;
        this.team = team;
        this.teamId = teamId;
        this.playedGames = playedGames;
        this.points = points;
        this.goals = goals;
        this.goalsAgainst = goalsAgainst;
        this.goalDifference = goalDifference;
        this.groupLetter = groupLetter;
    }

    public int getId() {
        return id;
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

    public String getGroupLetter() {
        return groupLetter;
    }

    public void setGroupLetter(String groupLetter) {
        this.groupLetter = groupLetter;
    }
}
