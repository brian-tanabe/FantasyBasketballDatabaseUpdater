package com.btanabe2.fbdu.dp.stats.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.btanabe2.fbdu.dp.web.WebConstants.SPORTS_VU_ALL_PLAYERS_URL;

/**
 * Created by BTanabe on 11/19/2014.
 */
public class PlayerProfileSportsVuScraper {
    // Check out this project:
    // https://www.npmjs.org/package/nba

    // Aaron Afflalo game logs: http://stats.nba.com/stats/playerprofile?LeagueID=00&Season=2014-15&IsOnlyCurrentSeason=1&PlayerId=201167&GraphStat=PTS&GraphEndSeason=2014-15&GraphStartSeason=1990-91&SeasonType=Regular%20Season
    // Aaron Afflalo player info: http://stats.nba.com/stats/commonplayerinfo?PlayerID=201167
    // All players: http://stats.nba.com/stats/commonallplayers?LeagueID=00&Season=2014-15&IsOnlyCurrentSeason=1

    private WebRequest webRequest;

    public PlayerProfileSportsVuScraper(WebRequest webRequest){
        this.webRequest = webRequest;
    }

    public List<PlayerBiographyEntity> scrapeForPlayerBiographies() throws IOException {
        List<Integer> allActiveNbaPlayersSportsVuPlayerIds = getListOfAllActivePlayerIds();
        return scrapeAllPlayerInfoFromEachPlayersSportsVuPage(allActiveNbaPlayersSportsVuPlayerIds);
    }

    private List<Integer> getListOfAllActivePlayerIds() throws IOException {
        JsonObject jsonElement = new JsonParser().parse(webRequest.getPage(SPORTS_VU_ALL_PLAYERS_URL)).getAsJsonObject();
        JsonArray jsonArray = jsonElement.getAsJsonArray("resultSets");
        JsonArray elements = (JsonArray) jsonArray.get(0).getAsJsonObject().get("rowSet");

        List<Integer> playerIds = new ArrayList<>(elements.size());
        elements.forEach(p -> playerIds.add(p.getAsJsonArray().get(0).getAsInt()));

        return playerIds;
    }

    private List<PlayerBiographyEntity> scrapeAllPlayerInfoFromEachPlayersSportsVuPage(List<Integer> allActivePlayerIds){
        List<PlayerBiographyEntity> playerBiographies = new ArrayList<>(allActivePlayerIds.size());
        allActivePlayerIds.forEach(p -> playerBiographies.add(getPlayerInfo(p)));

        return playerBiographies;
    }

    private PlayerBiographyEntity getPlayerInfo(int playerId){
        PlayerBiographyEntity player = new PlayerBiographyEntity();
        player.setId(playerId);


        return player;
    }
}
