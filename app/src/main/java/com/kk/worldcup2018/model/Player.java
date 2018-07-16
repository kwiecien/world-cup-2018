package com.kk.worldcup2018.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import org.parceler.Parcel;

@Parcel
@Entity(
        foreignKeys = @ForeignKey(entity = Team.class, parentColumns = "name", childColumns = "teamName"),
        indices = {@Index("teamName")}
)
public class Player {
    @PrimaryKey(autoGenerate = true)
    int id;
    String name;
    String position;
    int jerseyNumber;
    String teamName;

    @Ignore
    public Player() {
        // Necessary for Parcels
    }

    @Ignore
    public Player(String name, String position, int jerseyNumber, String teamName) {
        this.name = name;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
        this.teamName = teamName;
    }

    public Player(int id, String name, String position, int jerseyNumber, String teamName) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
        this.teamName = teamName;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPosition() {
        return position;
    }

    public int getJerseyNumber() {
        return jerseyNumber;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", position='" + position + '\'' +
                ", jerseyNumber=" + jerseyNumber +
                '}';
    }
}