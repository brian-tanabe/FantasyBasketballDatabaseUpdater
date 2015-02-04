package com.btanabe2.fbdu.dp.fixtures;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_FANTASY_BASKETBALL_HOMEPAGE;

/**
 * Created by Brian on 2/4/15.
 */
public class EspnLeagueIdScraperFixture {

    public static Map<String, String> getEspnLeagueIdScraperPagesMappedToTheirUrls() throws IOException {
        Map<String, String> urlToPageMap = new LinkedHashMap<>();
        urlToPageMap.put(ESPN_FANTASY_BASKETBALL_HOMEPAGE, FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-fantasy-homepage.html"), Charset.forName("UTF8")));

        return urlToPageMap;
    }
}
