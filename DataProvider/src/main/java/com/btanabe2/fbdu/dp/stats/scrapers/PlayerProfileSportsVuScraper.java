package com.btanabe2.fbdu.dp.stats.scrapers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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

    public PlayerProfileSportsVuScraper(WebRequest webRequest) {
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
        for (int playerId : allActivePlayerIds) { // is there a way to do lambdas that throw exceptions?
            playerBiographies.add(getPlayerInfo(playerId, nbaTeam));
        }

        return playerBiographies;
    }

    private PlayerBiographyEntity getPlayerInfo(int playerId, List<NbaTeamEntity> nbaTeams) throws IOException, ParseException {
        JsonArray playerInfoJsonArray = getPlayerInfoJsonArray(playerId);

        PlayerBiographyEntity player = new PlayerBiographyEntity();
        player.setId(playerId);
        player.setName(extractPlayerName(playerInfoJsonArray.get(3)));
        player.setBirthday(extractPlayerBirthday(playerInfoJsonArray.get(6)));
        player.setExperience(extractPlayerExperience(playerInfoJsonArray.get(12)));
        player.setNbateamid(extractNbaTeamId(playerInfoJsonArray.get(18), nbaTeams));
        player.setHeight(extractHeight(playerInfoJsonArray.get(10)));
        player.setWeight(extractWeight(playerInfoJsonArray.get(11)));
        player.setCountry(extractCountry(playerInfoJsonArray.get(8)));
        player.setSchool(extractCollege(playerInfoJsonArray.get(7)));

        return player;
    }

    private JsonArray getPlayerInfoJsonArray(int playerId) throws IOException {
        JsonObject jsonElement = new JsonParser().parse(webRequest.getPage(getPlayerInfoPageUrlFromSportsVu(playerId))).getAsJsonObject();
        JsonArray jsonArray = jsonElement.getAsJsonArray("resultSets");
        JsonArray elements = jsonArray.get(0).getAsJsonObject().get("rowSet").getAsJsonArray();

        return elements.get(0).getAsJsonArray();
    }

    private String extractPlayerName(JsonElement playerNameJsonElement) {
        try {
            return playerNameJsonElement.getAsString();
        } catch (Exception ex) {
            return "";
        }
    }

    private Date extractPlayerBirthday(JsonElement playerBirthdayJsonElement) {
        try {
            return new Date(new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(playerBirthdayJsonElement.getAsString()).getTime());
        } catch (Exception ex) {
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, 1900);
            calendar.set(Calendar.MONTH, 0);
            calendar.set(Calendar.DAY_OF_MONTH, 0);

            return new Date(calendar.getTimeInMillis());
        }
    }

    private int extractPlayerExperience(JsonElement playerExperienceJsonElement) {
        try {
            return playerExperienceJsonElement.getAsInt();
        } catch (Exception ex) {
            return 0;
        }
    }

    private int extractNbaTeamId(JsonElement playerNbaTeamIdJsonElement, List<NbaTeamEntity> nbaTeams) {
        try {
            return nbaTeams.stream().filter(t -> t.getAbbreviation().equals(playerNbaTeamIdJsonElement.getAsString())).limit(1).collect(Collectors.toList()).get(0).getId();
        } catch (Exception ex) {
            return 0;
        }
    }

    private int extractHeight(JsonElement playerHeightJsonElement) {
        try {
            String heightString = playerHeightJsonElement.getAsString();
            String[] feetInches = heightString.split("-");
            return Integer.parseInt(feetInches[0]) * 12 + Integer.parseInt(feetInches[1]);
        } catch (Exception ex) {
            return 0;
        }
    }

    private int extractWeight(JsonElement playerWeightJsonElement) {
        try {
            return playerWeightJsonElement.getAsInt();
        } catch (Exception ex) {
            return 0;
        }
    }

    private String extractCountry(JsonElement playerCountryJsonElement) {
        try {
            return playerCountryJsonElement.getAsString();
        } catch (Exception ex) {
            return "";
        }
    }

    private String extractCollege(JsonElement playerCollegeJsonElement) {
        try {
            return playerCollegeJsonElement.getAsString();
        } catch (Exception ex) {
            return "";
        }
    }
}
