package com.btanabe2.fbdu.dp.stats.scrapers;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by BTanabe on 11/19/2014.
 */
public class PlayerProfileSportsVuScraper {
    // Check out this project:
    // https://www.npmjs.org/package/nba

    // All players: http://stats.nba.com/stats/commonallplayers?LeagueID=00&Season=2014-15&IsOnlyCurrentSeason=1

    public List<Map<String, String>> getAllActivePlayersNameIdAndExperience(String playerJson) {
        Gson jsonParser = new Gson();
        

        return new ArrayList<>();
    }
}
