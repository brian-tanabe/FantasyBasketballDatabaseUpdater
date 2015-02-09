package com.btanabe2.fbdu.dp.mocks;

import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.WebRequest;
import com.btanabe2.fbdu.dp.web.auth.TestableCredentialProvider;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.util.Map;

import static com.btanabe2.fbdu.dp.fixtures.EspnFantasyIdToStandardIdProviderFixture.getEspnFantasyIdToStandardIdProviderPagesMappedToTheirUrls;
import static com.btanabe2.fbdu.dp.fixtures.EspnLeagueIdScraperFixture.getEspnLeagueIdScraperPagesMappedToTheirUrls;
import static com.btanabe2.fbdu.dp.fixtures.NbaTeamProviderFixture.getNbaTeamProviderMockWebRequestUrlsToPageStrings;
import static com.btanabe2.fbdu.dp.fixtures.PlayerBiographyProviderFixture.getPlayerProfilePagesMappedToTheirUrls;
import static com.btanabe2.fbdu.dp.fixtures.PositionEligibilityProviderFixture.getPositionEligibilityProviderPagesMappedToTheirUrlsMap;
import static com.btanabe2.fbdu.dp.fixtures.SportsVuPlayerProfileFixture.getSportsVuPlayerProfilePagesMappedToTheirUrls;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by brian on 11/6/14.
 */
public class MockWebRequest {

    public static WebRequest getPlayerBiographyProviderMockWebRequest() throws IOException {
        return getMockWebRequest(getPlayerProfilePagesMappedToTheirUrls());
    }

    public static WebRequest getSportsVuTeamsPageMockWebRequest() throws IOException {
        return getMockWebRequest(getNbaTeamProviderMockWebRequestUrlsToPageStrings());
    }

    public static WebRequest getPlayerProfileSportsVuScraperMockWebRequest() throws IOException {
        return getMockWebRequest(getSportsVuPlayerProfilePagesMappedToTheirUrls());
    }

    public static SecureWebRequest getEspnFantasyIdToStandardIdProviderMockWebRequest() throws IOException {
        return getMockSecureWebRequest(getEspnFantasyIdToStandardIdProviderPagesMappedToTheirUrls());
    }

    public static SecureWebRequest getEspnLeagueIdAndTeamIdScraperMockWebRequest() throws IOException {
        return getMockSecureWebRequest(getEspnLeagueIdScraperPagesMappedToTheirUrls());
    }

    public static SecureWebRequest getPositionEligibilityProviderMockSecureWebRequest() throws IOException {
        return getMockSecureWebRequest(getPositionEligibilityProviderPagesMappedToTheirUrlsMap());
    }

    private static WebRequest getMockWebRequest(Map<String, String> urlToPageStringMap) {
        try {
            WebRequest webRequest = mock(WebRequest.class);
            for (String url : urlToPageStringMap.keySet()) {
                when(webRequest.getPage(url)).thenReturn(urlToPageStringMap.get(url));
                when(webRequest.getPageAsDocument(url)).thenReturn(Jsoup.parse(urlToPageStringMap.get(url)));
            }

            return webRequest;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private static SecureWebRequest getMockSecureWebRequest(Map<String, String> urlToPageStringMap) {
        try {
            SecureWebRequest webRequest = mock(SecureWebRequest.class);
            for (String url : urlToPageStringMap.keySet()) {
                when(webRequest.getPageAsDocument(url)).thenReturn(Jsoup.parse(urlToPageStringMap.get(url)));
                when(webRequest.getPage(url)).thenReturn(urlToPageStringMap.get(url));
            }

            when(webRequest.login(new TestableCredentialProvider())).thenReturn(webRequest);
            return webRequest;
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }
}
