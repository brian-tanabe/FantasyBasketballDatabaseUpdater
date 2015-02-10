package com.btanabe2.fbdu.dp.data.scrapers.sportsvu;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.web.WebConstants.getPlayerInfoPageUrlFromSportsVu;

/**
 * Created by brian on 2/9/15.
 */
public class CallablePlayerProfileSportsVuScraper implements Callable<PlayerBiographyEntity> {
    private WebRequest webRequest;
    private int playerId;
    private List<NbaTeamEntity> nbaTeams;

    public CallablePlayerProfileSportsVuScraper(WebRequest webRequest, int playerId, List<NbaTeamEntity> nbaTeams) {
        this.webRequest = webRequest;
        this.playerId = playerId;
        this.nbaTeams = nbaTeams;
    }

    @Override
    public PlayerBiographyEntity call() throws Exception {
        JsonArray playerInfoJsonArray = getPlayerInfoJsonArray(playerId);
        if (extractRosterStatus(playerInfoJsonArray.get(15))) {
            return populatePlayerBiographyEntityObject(playerId, playerInfoJsonArray, nbaTeams);
        }

        return null;
    }

    private JsonArray getPlayerInfoJsonArray(int playerId) throws IOException {
        JsonObject jsonElement = new JsonParser().parse(webRequest.getPage(getPlayerInfoPageUrlFromSportsVu(playerId))).getAsJsonObject();
        JsonArray jsonArray = jsonElement.getAsJsonArray("resultSets");
        JsonArray elements = jsonArray.get(0).getAsJsonObject().get("rowSet").getAsJsonArray();

        return elements.get(0).getAsJsonArray();
    }

    private PlayerBiographyEntity populatePlayerBiographyEntityObject(int playerId, JsonArray playerInfoJsonArray, List<NbaTeamEntity> nbaTeams) {
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

    private boolean extractRosterStatus(JsonElement playerRosterStatusJsonElement) {
        try {
            return playerRosterStatusJsonElement.getAsString().equals("Active");
        } catch (Exception ex) {
            return false;
        }
    }
}
