package com.btanabe2.fbdu.dp.stats.scrapers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dp.models.NbaTeamModel;
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

    public static List<NbaTeamEntity> getAllNbaTeams() throws IOException {
        String nbaTeamsJsonFile = getNbaTeamsJsonFileAsString();
        return parseNbaTeamsJsonFile(nbaTeamsJsonFile);
    }

    private static String getNbaTeamsJsonFileAsString() throws IOException {
        return FileUtils.readFileToString(new File("./DataProvider/src/main/resources/nba-teams.json"), Charset.forName("UTF8"));
    }

    private static List<NbaTeamEntity> parseNbaTeamsJsonFile(String nbaTeamsJsonString){
        List<NbaTeamEntity> nbaTeamsList = new ArrayList<>(30);

        Gson gson = new GsonBuilder().create();
        NbaTeamModel[] nbaTeams = gson.fromJson(nbaTeamsJsonString, NbaTeamModel[].class);

        for (NbaTeamModel nbaTeam : nbaTeams) {
            NbaTeamEntity newNbaTeamEntity = new NbaTeamEntity();
            newNbaTeamEntity.setName(nbaTeam.name);
            newNbaTeamEntity.setLocation(nbaTeam.location);
            newNbaTeamEntity.setAbbreviation(nbaTeam.abbreviation);
            nbaTeamsList.add(newNbaTeamEntity);
        }

        return nbaTeamsList;
    }
}
