package com.kk.worldcup2018.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.List;

@Parcel
public class Group {
    @SerializedName("group")
    String letter;
    List<Standings> standingsList;

    public Group() {
        // Necessary for Parcels
    }

    public Group(String letter, List<Standings> standingsList) {
        this.letter = letter;
        this.standingsList = standingsList;
    }

    public String getLetter() {
        return letter;
    }

    public List<Standings> getStandingsList() {
        return standingsList;
    }
}
