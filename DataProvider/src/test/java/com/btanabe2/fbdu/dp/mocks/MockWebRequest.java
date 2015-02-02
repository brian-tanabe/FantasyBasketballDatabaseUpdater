package com.btanabe2.fbdu.dp.mocks;

import com.btanabe2.fbdu.dp.fixtures.FileDocumentor;
import com.btanabe2.fbdu.dp.fixtures.SportsVuPlayerProfileFixture;
import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.btanabe2.fbdu.dp.web.auth.TestableCredentialProvider;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.btanabe2.fbdu.dp.fixtures.EspnTeamsPageFixture.getEspnFantasyBasketballHomepagePage;
import static com.btanabe2.fbdu.dp.web.WebConstants.*;
import static org.mockito.Matchers.contains;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by brian on 11/6/14.
 */
public class MockWebRequest {

    public static WebRequest getPlayerBiographyProviderMockWebRequest(){
        WebRequest webRequest = mock(WebRequest.class);

        try {
            when(webRequest.getPage(SPORTS_VU_ALL_PLAYERS_URL)).thenReturn(FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-sportsvu-pages/nba-commonallplayers.json"), Charset.forName("UTF8")));
            when(webRequest.getPage(contains("?PlayerID="))).thenReturn(FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-sportsvu-pages/playerinfo-pages/201167.json"), Charset.forName("UTF8")));
            when(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL)).thenReturn(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-guards.html"));
            when(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_FORWARDS_URL)).thenReturn(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-forwards.html"));
            when(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_CENTERS_URL)).thenReturn(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-centers.html"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return webRequest;
        }
    }

    public static WebRequest getSportsVuTeamsPageMockWebRequest(){
        WebRequest webRequest = mock(WebRequest.class);
        try {
            when(webRequest.getPage(SPORTS_VU_NBA_TEAM_INFO_URL)).thenReturn(FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-sportsvu-pages/nba-commonteamyear.json")));
            when(webRequest.getPageAsDocument(NUMBER_FIRE_REMAINING_PROJECTIONS_GUARDS_URL)).thenReturn(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-guards.html"));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            return webRequest;
        }
    }

    public static WebRequest getPlayerProfileSportsVuScraperMockWebRequest() throws IOException {
        return getMockWebRequestUsingPageStrings(SportsVuPlayerProfileFixture.getSportsVuPlayerProfilePagesMappedToTheirUrls());
    }

    public static SecureWebRequest getEspnLeagueIdScraperMockWebRequest() throws IOException {
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<>();
        urlToDocumentMap.put(ESPN_FANTASY_BASKETBALL_HOMEPAGE, getEspnFantasyBasketballHomepagePage());

        return getMockSecureWebRequest(urlToDocumentMap);
    }

    private static WebRequest getMockWebRequestUsingPageStrings(Map<String, String> urlToPageStringMap) {
        try {
            WebRequest webRequest = mock(WebRequest.class);
            for (String url : urlToPageStringMap.keySet()) {
                when(webRequest.getPage(url)).thenReturn(urlToPageStringMap.get(url));
                when(webRequest.getPageAsDocument(url)).thenReturn(Jsoup.parse(urlToPageStringMap.get(url)));
            }

            return webRequest;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    private static SecureWebRequest getMockSecureWebRequest(Map<String, Document> urlToDocumentMap){
        try {
            SecureWebRequest webRequest = mock(SecureWebRequest.class);
            for(String url : urlToDocumentMap.keySet()){
                when(webRequest.getPageAsDocument(url)).thenReturn(urlToDocumentMap.get(url));
                when(webRequest.getPage(url)).thenReturn(urlToDocumentMap.get(url).html());
            }

            when(webRequest.login(new TestableCredentialProvider())).thenReturn(webRequest);
            return webRequest;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
