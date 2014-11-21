package com.btanabe2.fbdu.dp.stats.scrapers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.btanabe2.fbdu.dp.web.WebConstants.SPORTS_VU_ALL_PLAYERS_URL;
import static com.btanabe2.fbdu.dp.web.WebConstants.getPlayerInfoPageUrlFromSportsVu;

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

    public List<PlayerBiographyEntity> scrapeForPlayerBiographies(List<NbaTeamEntity> nbaTeams) throws IOException, ParseException {
        List<Integer> allActiveNbaPlayersSportsVuPlayerIds = getListOfAllActivePlayerIds();
        return scrapeAllPlayerInfoFromEachPlayersSportsVuPage(allActiveNbaPlayersSportsVuPlayerIds, nbaTeams);
    }

    private List<Integer> getListOfAllActivePlayerIds() throws IOException {
        // TODO THIS CAN BE ABSTRACTED OUT:
        JsonObject jsonElement = new JsonParser().parse(webRequest.getPage(SPORTS_VU_ALL_PLAYERS_URL)).getAsJsonObject();
        JsonArray jsonArray = jsonElement.getAsJsonArray("resultSets");
        JsonArray elements = (JsonArray) jsonArray.get(0).getAsJsonObject().get("rowSet");

        List<Integer> playerIds = new ArrayList<>(elements.size());
        elements.forEach(p -> playerIds.add(p.getAsJsonArray().get(0).getAsInt()));

        return playerIds;
    }

    private List<PlayerBiographyEntity> scrapeAllPlayerInfoFromEachPlayersSportsVuPage(List<Integer> allActivePlayerIds, List<NbaTeamEntity> nbaTeam) throws IOException, ParseException {
        List<PlayerBiographyEntity> playerBiographies = new ArrayList<>(allActivePlayerIds.size());
        for(int playerId : allActivePlayerIds){ // is there a way to do lambdas that throw execptions?
            playerBiographies.add(getPlayerInfo(playerId, nbaTeam));
        }

        return playerBiographies;
    }

    private PlayerBiographyEntity getPlayerInfo(int playerId, List<NbaTeamEntity> nbaTeam) throws IOException, ParseException {
        JsonArray playerInfoJsonArray = getPlayerInfoJsonArray(playerId);

        PlayerBiographyEntity player = new PlayerBiographyEntity();
        player.setId(playerId);
        player.setName(playerInfoJsonArray.get(3).getAsString());
        player.setBirthday(convertDateStringToSqlDateObject(playerInfoJsonArray.get(6).getAsString()));
        player.setExperience(playerInfoJsonArray.get(12).getAsInt());
        player.setNbateamid(convertTeamNameToTeamId(playerInfoJsonArray.get(19).getAsString(), nbaTeam));
        player.setHeight(convertHeightToInches(playerInfoJsonArray.get(10).getAsString()));
        player.setWeight(playerInfoJsonArray.get(11).getAsInt());
        player.setCountry(playerInfoJsonArray.get(8).getAsString());
        player.setSchool(playerInfoJsonArray.get(7).getAsString());

        return player;
    }

    private JsonArray getPlayerInfoJsonArray(int playerId) throws IOException {
        JsonObject jsonElement = new JsonParser().parse(webRequest.getPage(getPlayerInfoPageUrlFromSportsVu(playerId))).getAsJsonObject();
        JsonArray jsonArray = jsonElement.getAsJsonArray("resultSets");

//        JsonArray elementHeaders = jsonArray.get(0).getAsJsonObject().get("headers").getAsJsonArray();
        JsonArray elements = jsonArray.get(0).getAsJsonObject().get("rowSet").getAsJsonArray();

        return elements.get(0).getAsJsonArray();
    }

    private int convertHeightToInches(String heightString){
        String[] feetInches = heightString.split("-");
        return Integer.parseInt(feetInches[1]) * 12 + Integer.parseInt(feetInches[0]);
    }

    private Date convertDateStringToSqlDateObject(String dateString) throws ParseException {
        return new Date(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(dateString).getTime());
    }

    private int convertTeamNameToTeamId(String teamName, List<NbaTeamEntity> nbaTeam){

        return 0;
    }
}
