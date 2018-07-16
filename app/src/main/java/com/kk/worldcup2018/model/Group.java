package com.kk.worldcup2018.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

@Parcel
@Entity(indices = @Index(value = "letter", unique = true))
public class Group {
    @PrimaryKey(autoGenerate = true)
    int id;
    @SerializedName("group")
    String letter;
    @Ignore
    List<Standings> standingsList; // TODO add standings and players table

    @Ignore
    public Group() {
        // Necessary for Parcels
    }

    public Group(int id, String letter) {
        this.id = id;
        this.letter = letter;
        standingsList = new ArrayList<>();
    }

    @Ignore
    public Group(String letter, List<Standings> standingsList) {
        this.letter = letter;
        standingsList.forEach(standings -> standings.setGroupLetter(letter));
        this.standingsList = standingsList;
    }

    public int getId() {
        return id;
    }

    public String getLetter() {
        return letter;
    }

    public List<Standings> getStandingsList() {
        return standingsList;
    }

    public void setStandingsList(List<Standings> standings) {
        standingsList = standings;
    }
}
