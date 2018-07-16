package com.kk.worldcup2018.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;
import com.kk.worldcup2018.data.response.TeamsResponse;

import org.parceler.Parcel;

@Parcel
@Entity(
        indices = @Index(value = "name", unique = true)
)
public class Team {
    @PrimaryKey(autoGenerate = true)
    int id;
    int teamId;
    String name;
    String code;
    @ColumnInfo(name = "crest_url")
    String crestUrl;
    @Ignore
    @SerializedName("_links")
    TeamsResponse.TeamId responseTeamId;

    @Ignore
    public Team() {
        // Necessary for Parcels
    }

    @Ignore
    public Team(int teamId, String name, String code, String crestUrl) {
        this.teamId = teamId;
        this.name = name;
        this.code = code;
        this.crestUrl = crestUrl;
    }

    // Room constructor
    public Team(int id, int teamId, String name, String code, String crestUrl) {
        this.id = id;
        this.teamId = teamId;
        this.name = name;
        this.code = code;
        this.crestUrl = crestUrl;
    }

    public int getId() {
        return id;
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

    public int getResponseTeamId() {
        return responseTeamId.getId();
    }
}
