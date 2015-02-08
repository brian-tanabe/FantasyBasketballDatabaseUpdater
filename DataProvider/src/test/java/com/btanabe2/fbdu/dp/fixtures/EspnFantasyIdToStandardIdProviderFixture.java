package com.btanabe2.fbdu.dp.fixtures;

import com.btanabe2.fbdu.dp.web.WebConstants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnFantasyIdToStandardIdProviderFixture {

    public static Map<String, String> getEspnFantasyIdToStandardIdProviderPagesMappedToTheirUrls() throws IOException {
        Map<String, String> urlToPageString = new LinkedHashMap<>();
        urlToPageString.put(WebConstants.ESPN_TEAMS_PAGE_URL, FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-team-pages/espn-teams-page.html"), Charset.forName("UTF8")));

        return urlToPageString;
    }
}
