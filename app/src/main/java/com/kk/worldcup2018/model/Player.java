package com.kk.worldcup2018.model;

import org.parceler.Parcel;

@Parcel
public class Player {
    String name;
    String position;
    int jerseyNumber;

    public Player() {
        // Necessary for Parcels
    }

    public Player(String name, String position, int jerseyNumber) {
        this.name = name;
        this.position = position;
        this.jerseyNumber = jerseyNumber;
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
}