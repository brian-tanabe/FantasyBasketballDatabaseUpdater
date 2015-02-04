package com.btanabe2.fbdu.dp.data.scrapers;

import com.btanabe2.fbdu.dp.data.models.NbaTeamModel;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by brian on 11/6/14.
 */
public class NbaTeamJsonParser {

    // Check out:
    // http://stats.nba.com/stats/commonteamyears?LeagueID=00

    public static List<NbaTeamModel> getAllNbaTeams() throws IOException {
        String nbaTeamsJsonFile = getNbaTeamsJsonFileAsString();
        return parseNbaTeamsJsonFile(nbaTeamsJsonFile);
    }

    private static String getNbaTeamsJsonFileAsString() throws IOException {
        return FileUtils.readFileToString(new File("./DataProvider/src/main/resources/nba-teams.json"), Charset.forName("UTF8"));
    }

    private static List<NbaTeamModel> parseNbaTeamsJsonFile(String nbaTeamsJsonString) {
        Gson gson = new GsonBuilder().create();
        NbaTeamModel[] nbaTeams = gson.fromJson(nbaTeamsJsonString, NbaTeamModel[].class);

        List<NbaTeamModel> models = new ArrayList<>(30);
        for (NbaTeamModel nbaTeam : nbaTeams) {
            models.add(nbaTeam);
        }

        return models;
    }
}
