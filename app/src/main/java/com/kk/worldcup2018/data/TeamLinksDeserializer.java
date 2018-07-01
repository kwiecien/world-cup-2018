package com.kk.worldcup2018.data;

import android.net.Uri;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.kk.worldcup2018.data.response.TeamsResponse;

import java.lang.reflect.Type;

public class TeamLinksDeserializer implements JsonDeserializer<TeamsResponse.TeamId> {
    @Override
    public TeamsResponse.TeamId deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) {
        JsonElement team = json.getAsJsonObject();
        String href = team.getAsJsonObject().get("self")
                .getAsJsonObject().get("href").getAsString();
        String id = Uri.parse(href).getLastPathSegment();
        return new TeamsResponse.TeamId(id);
    }
}
