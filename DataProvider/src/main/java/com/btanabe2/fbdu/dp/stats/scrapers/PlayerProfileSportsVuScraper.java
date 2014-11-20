package com.btanabe2.fbdu.dp.stats.scrapers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BTanabe on 11/19/2014.
 */
public class PlayerProfileSportsVuScraper {
    // Check out this project:
    // https://www.npmjs.org/package/nba

    // Aaron Afflalo game logs: http://stats.nba.com/stats/playerprofile?LeagueID=00&Season=2014-15&IsOnlyCurrentSeason=1&PlayerId=201167&GraphStat=PTS&GraphEndSeason=2014-15&GraphStartSeason=1990-91&SeasonType=Regular%20Season
    // All players: http://stats.nba.com/stats/commonallplayers?LeagueID=00&Season=2014-15&IsOnlyCurrentSeason=1

    public List<Map<String, String>> getAllActivePlayersNameIdAndExperience(String playerJson) {
        JsonObject jsonElement = new JsonParser().parse(playerJson).getAsJsonObject();
        JsonArray jsonArray = jsonElement.getAsJsonArray("resultSets");
        JsonArray elements = (JsonArray) jsonArray.get(0).getAsJsonObject().get("rowSet");

        return new ArrayList<>();
    }
}
