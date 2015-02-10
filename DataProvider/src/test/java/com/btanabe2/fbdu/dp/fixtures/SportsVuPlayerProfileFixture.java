package com.btanabe2.fbdu.dp.fixtures;

import com.btanabe2.fbdu.dp.web.WebConstants;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.btanabe2.fbdu.dp.web.WebConstants.SPORTS_VU_ALL_PLAYERS_URL;

/**
 * Created by brian on 2/2/15.
 */
public class SportsVuPlayerProfileFixture {

    public static Map<String, String> getSportsVuPlayerProfilePagesMappedToTheirUrls() throws IOException {
        Map<String, String> urlToDocumentMap = new LinkedHashMap<>();

        urlToDocumentMap.put(SPORTS_VU_ALL_PLAYERS_URL, FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-workers-pages/nba-commonallplayers.json"), Charset.forName("UTF8")));

        for (File testFile : FileUtils.listFiles(new File("./DataProvider/src/test/resources/webpages/nba-workers-pages/playerinfo-pages"), new String[]{"json"}, false)) {
            urlToDocumentMap.put(WebConstants.getPlayerInfoPageUrlFromSportsVu(Integer.parseInt(testFile.getName().replace(".json", ""))), FileUtils.readFileToString(new File(testFile.getAbsolutePath()), Charset.forName("UTF8")));
        }

        return urlToDocumentMap;
    }
}
