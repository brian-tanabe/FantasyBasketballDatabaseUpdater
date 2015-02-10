package com.btanabe2.fbdu.dp.fixtures;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.btanabe2.fbdu.dp.web.WebConstants.NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL;
import static com.btanabe2.fbdu.dp.web.WebConstants.SPORTS_VU_NBA_TEAM_INFO_URL;

/**
 * Created by Brian on 2/4/15.
 */
public class NbaTeamProviderFixture {

    public static Map<String, String> getNbaTeamProviderMockWebRequestUrlsToPageStrings() throws IOException {
        Map<String, String> urlToPageStringMap = new LinkedHashMap<>();
        urlToPageStringMap.put(SPORTS_VU_NBA_TEAM_INFO_URL, FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-workers-pages/nba-commonteamyear.json"), Charset.forName("UTF8")));
        urlToPageStringMap.put(NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL, FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-guards.html"), Charset.forName("UTF8")));
        return urlToPageStringMap;
    }
}
