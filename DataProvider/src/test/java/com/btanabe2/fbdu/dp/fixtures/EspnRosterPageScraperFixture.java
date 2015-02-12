package com.btanabe2.fbdu.dp.fixtures;

import com.btanabe2.fbdu.dp.web.WebConstants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Brian on 2/11/15.
 */
public class EspnRosterPageScraperFixture {
    public static final int TEST_LEAGUE_ID = 233928;

    public static Map<String, String> getEspnRosterPageScraperPagesMappedToTheirUrls() throws IOException {
        Map<String, String> urlToPageStringMap = new HashMap<>();
        urlToPageStringMap.put(WebConstants.getEspnRosterPageUrlForParameterizedLeagueId(TEST_LEAGUE_ID), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-roster-page.html"), Charset.forName("UTF8")));
        return urlToPageStringMap;
    }
}
