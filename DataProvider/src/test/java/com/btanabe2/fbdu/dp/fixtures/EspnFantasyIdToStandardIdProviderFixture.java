package com.btanabe2.fbdu.dp.fixtures;

import com.btanabe2.fbdu.dp.web.WebConstants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnFantasyIdToStandardIdProviderFixture {

    public static Map<String, String> getEspnFantasyIdToStandardIdProviderPagesMappedToTheirUrls() throws IOException {
        Map<String, String> urlToPageString = new HashMap<>();
        urlToPageString.put(WebConstants.ESPN_TEAMS_PAGE_URL, FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-team-pages/espn-teams-page.html"), Charset.forName("UTF8")));

        urlToPageString.putAll(getEspnTeamRosterPagesMappedToTheirUrlsMap());

        return urlToPageString;
    }

    private static Map<String, String> getEspnTeamRosterPagesMappedToTheirUrlsMap() throws IOException {
        List<File> testFiles = new ArrayList<>(30);
        Files.list(new File("./DataProvider/src/test/resources/webpages/espn-team-pages/").toPath()).filter(f -> f.toFile().getName().startsWith("espn-team-roster")).forEach(f -> testFiles.add(f.toFile()));

        Map<String, String> urlToPageStringMap = new HashMap<>(30);
        for (File testTeamRosterPageFile : testFiles) {
            urlToPageStringMap.put(String.format("http://espn.go.com/nba/team/roster/_/name/%s", testTeamRosterPageFile.getName().substring(testTeamRosterPageFile.getName().lastIndexOf("-")).replace("-", "")).replace(".html", ""), FileUtils.readFileToString(testTeamRosterPageFile, Charset.forName("UTF8")));
        }

        return urlToPageStringMap;
    }
}
