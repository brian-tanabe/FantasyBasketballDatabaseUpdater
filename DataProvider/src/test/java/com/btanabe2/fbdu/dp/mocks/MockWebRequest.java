package com.btanabe2.fbdu.dp.mocks;

import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.btanabe2.fbdu.dp.web.auth.TestableCredentialProvider;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

import static com.btanabe2.fbdu.dp.fixtures.EspnTeamsPageFixture.getEspnFantasyBasketballHomepagePage;
import static com.btanabe2.fbdu.dp.fixtures.NbaTeamProviderFixture.getNbaTeamProviderMockWebRequestUrlsToPageStrings;
import static com.btanabe2.fbdu.dp.fixtures.PlayerBiographyProviderFixture.getPlayerProfilePagesMappedToTheirUrls;
import static com.btanabe2.fbdu.dp.fixtures.SportsVuPlayerProfileFixture.getSportsVuPlayerProfilePagesMappedToTheirUrls;
import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_FANTASY_BASKETBALL_HOMEPAGE;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by brian on 11/6/14.
 */
public class MockWebRequest {

    public static WebRequest getPlayerBiographyProviderMockWebRequest() throws IOException {
        return getMockWebRequestUsingPageStrings(getPlayerProfilePagesMappedToTheirUrls());
    }

    public static WebRequest getSportsVuTeamsPageMockWebRequest() throws IOException {
        return getMockWebRequestUsingPageStrings(getNbaTeamProviderMockWebRequestUrlsToPageStrings());
    }

    public static WebRequest getPlayerProfileSportsVuScraperMockWebRequest() throws IOException {
        return getMockWebRequestUsingPageStrings(getSportsVuPlayerProfilePagesMappedToTheirUrls());
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
