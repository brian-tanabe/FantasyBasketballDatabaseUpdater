package com.btanabe2.fbdu.dp.fixtures;

import com.btanabe2.fbdu.dp.web.WebConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.IOFileFilter;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.*;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnFantasyIdToStandardIdProviderFixture {

    public static Map<String, String> getEspnFantasyIdToStandardIdProviderPagesMappedToTheirUrls() throws IOException {
        Map<String, String> urlToPageString = new HashMap<>();

        urlToPageString.put(WebConstants.ESPN_TEAMS_PAGE_URL, FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-team-pages/espn-teams-page.html"), Charset.forName("UTF8")));
        urlToPageString.putAll(getEspnTeamRosterPagesMappedToTheirUrlsMap());
        urlToPageString.putAll(getEspnPlayerProfilePagesMappedToTheirUrlsMap());

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

    private static Map<String, String> getEspnPlayerProfilePagesMappedToTheirUrlsMap() throws IOException {
        Collection<File> allPlayerProfilePageFiles = FileUtils.listFiles(new File("./DataProvider/src/test/resources/webpages/espn-player-pages/"), new IOFileFilter() {
            @Override
            public boolean accept(File file) {
                return true;
            }

            @Override
            public boolean accept(File dir, String name) {
                return true;
            }
        }, new IOFileFilter() {
            @Override
            public boolean accept(File file) {
                return false;
            }

            @Override
            public boolean accept(File dir, String name) {
                return false;
            }
        });

        Map<String, String> playerPagesMappedToTheirUrls = new HashMap<>(allPlayerProfilePageFiles.size());
        for (File playerProfilePageFile : allPlayerProfilePageFiles) {
            playerPagesMappedToTheirUrls.put(String.format("http://espn.go.com/nba/player/_/id/%s", playerProfilePageFile.getName().replace("espn-player-profile-page-", "").replace("_", "/")).replace(".html", ""), FileUtils.readFileToString(playerProfilePageFile, Charset.forName("UTF8")));
        }
        return playerPagesMappedToTheirUrls;
    }
}
