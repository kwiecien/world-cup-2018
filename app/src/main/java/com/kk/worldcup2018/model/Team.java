package com.kk.worldcup2018.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.kk.worldcup2018.data.response.TeamsResponse;

import org.parceler.Parcel;

import java.util.List;

@Parcel
@Entity
public class Team {
    @PrimaryKey(autoGenerate = true)
    int id;
    int teamId;
    String name;
    String code;
    @ColumnInfo(name = "crest_url")
    String crestUrl;
    @Ignore
    List<Player> players;
    @Ignore
    @SerializedName("_links")
    TeamsResponse.TeamId responseTeamId;

    @Ignore
    public Team() {
        // Necessary for Parcels
    }

    @Ignore
    public Team(int teamId, String name, String code, String crestUrl, List<Player> players) {
        this.teamId = teamId;
        this.name = name;
        this.code = code;
        this.crestUrl = crestUrl;
        this.players = players;
    }

    // Room constructor
    public Team(int id, int teamId, String name, String code, String crestUrl) {
        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.code = code;
        this.crestUrl = crestUrl;
    }

    public int getTeamId() {
        return teamId;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public String getCrestUrl() {
        return crestUrl;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public int getResponseTeamId() {
        return responseTeamId.getId();
    }
}
