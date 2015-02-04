package com.btanabe2.fbdu.dp.fixtures;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.btanabe2.fbdu.dp.web.WebConstants.getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId;

/**
 * Created by Brian on 2/4/15.
 */
public class PositionEligibilityProviderFixture {

    public static Map<String, String> getPositionEligibilityProviderPagesMappedToTheirUrlsMap() throws IOException {
        Map<String, String> urlToPageStringMap = new LinkedHashMap<>();
        urlToPageStringMap.put(getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(233928, 5, 0), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-playerrater1.html")));
        urlToPageStringMap.put(getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(233928, 5, 1), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-playerrater2.html")));
        urlToPageStringMap.put(getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(233928, 5, 2), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-playerrater3.html")));
        urlToPageStringMap.put(getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(233928, 5, 3), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-playerrater4.html")));
        urlToPageStringMap.put(getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(233928, 5, 4), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-playerrater5.html")));
        urlToPageStringMap.put(getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(233928, 5, 5), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-playerrater6.html")));
        urlToPageStringMap.put(getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(233928, 5, 6), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-playerrater7.html")));
        urlToPageStringMap.put(getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(233928, 5, 7), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-playerrater8.html")));
        urlToPageStringMap.put(getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(233928, 5, 8), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-playerrater9.html")));
        urlToPageStringMap.put(getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(233928, 5, 9), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-playerrater10.html")));
        urlToPageStringMap.put(getEspnPlayerRaterPageUrlForParameterizedLeagueIdAndTeamId(233928, 5, 10), FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/espn-fantasy-pages/espn-playerrater11.html")));
        return urlToPageStringMap;
    }
}
