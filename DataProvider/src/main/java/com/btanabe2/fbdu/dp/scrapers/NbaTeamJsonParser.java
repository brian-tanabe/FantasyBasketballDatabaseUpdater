package com.btanabe2.fbdu.dp.scrapers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
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
        List<NbaTeamEntity> nbaTeams = new ArrayList<>(30);

        String jsonFileString = getNbaJsonFileAsString();

        return nbaTeams;
    }

    private static String getNbaJsonFileAsString() throws IOException {
        return FileUtils.readFileToString(new File("./DataProvider/src/main/resources/nba-teams.json"), Charset.forName("UTF8"));
    }
}
