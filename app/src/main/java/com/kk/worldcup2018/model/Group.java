package com.kk.worldcup2018.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Data;

@Data
public class Group {
    @SerializedName("group")
    private final String letter;
    private final List<Standings> standingsList;
}
