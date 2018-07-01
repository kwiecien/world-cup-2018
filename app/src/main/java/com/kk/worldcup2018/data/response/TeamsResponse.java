package com.kk.worldcup2018.data.response;

import android.support.annotation.NonNull;

import com.kk.worldcup2018.model.Team;

import org.parceler.Parcel;

import java.util.List;

public class TeamsResponse extends Response<Team> {

    @Override
    @NonNull
    public List<Team> getObjects() {
        for (Team team : teams) {
            team.setTeamId(team.getResponseTeamId());
        }
        return teams;
    }

    @Parcel
    public static class TeamId {
        int id;

        public TeamId() {
            // default empty bean constructor for Parcel
        }

        public TeamId(String id) {
            this.id = Integer.parseInt(id);
        }

        public int getId() {
            return id;
        }
    }

}