package com.btanabe2.fbdu.dp.stats.scrapers;

import com.btanabe2.fbdu.dp.leagues.providers.CurrentNbaSeasonStartYearProvider;
import com.btanabe2.fbdu.dp.models.NbaTeamSportsVuModel;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static com.btanabe2.fbdu.dp.web.WebConstants.SPORTS_VU_NBA_TEAM_INFO_URL;

/**
 * Created by BTanabe on 11/21/2014.
 */
public class NbaTeamSportsVuScraper {
    private WebRequest webRequest;

    public NbaTeamSportsVuScraper(WebRequest webRequest){
        this.webRequest = webRequest;
    }

    public List<NbaTeamSportsVuModel> getAllNbaTeams() throws IOException {
        // TODO THIS CAN BE ABSTRACTED OUT:
        JsonObject jsonElement = new JsonParser().parse(webRequest.getPage(SPORTS_VU_NBA_TEAM_INFO_URL)).getAsJsonObject();
        JsonArray jsonArray = jsonElement.getAsJsonArray("resultSets");
        JsonArray elements = (JsonArray) jsonArray.get(0).getAsJsonObject().get("rowSet");

        List<NbaTeamSportsVuModel> nbaTeams = new ArrayList<>(elements.size());
        for(int index = 0; index < elements.size(); index++){
            JsonArray teamJsonArray = elements.get(index).getAsJsonArray();
            if(teamJsonArray.get(3).getAsInt() == CurrentNbaSeasonStartYearProvider.getCurrentNbaSeasonStartYear(Calendar.getInstance())) {
                nbaTeams.add(parseOutTeamInfo(teamJsonArray));
            }
        }

        return nbaTeams;
    }

    private NbaTeamSportsVuModel parseOutTeamInfo(JsonArray teamJsonArray){
        int id = teamJsonArray.get(1).getAsInt();
        String abbreviation = teamJsonArray.get(4).getAsString();

        return new NbaTeamSportsVuModel(id, abbreviation);
    }
}
