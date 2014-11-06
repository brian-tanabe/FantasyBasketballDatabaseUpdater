package com.btanabe2.fbdu.dp.mocks;

import com.btanabe2.fbdu.dp.web.WebRequest;
import org.jsoup.nodes.Document;

import java.util.LinkedHashMap;
import java.util.Map;

import static com.btanabe2.fbdu.dp.fixtures.BasketballReferencePageFixture.*;
import static com.btanabe2.fbdu.dp.fixtures.EspnTeamsPageFixture.*;
import static com.btanabe2.fbdu.dp.fixtures.EspnTeamsPageFixture.getEspnTeamsPage;
import static com.btanabe2.fbdu.dp.web.WebConstants.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by brian on 11/6/14.
 */
public class MockWebRequest {

    public static WebRequest getEspnTeamsPageScraperMockWebRequest() {
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<>();
        urlToDocumentMap.put(ESPN_TEAMS_PAGE_URL, getEspnTeamsPage());
        urlToDocumentMap.put(ESPN_TEAMS_BOS_URL, getEspnBostonStadiumPage());

        return getMockWebRequest(urlToDocumentMap);
    }

    public static WebRequest getBasketballReferenceTeamsPageMockWebRequest(){
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<>();
        urlToDocumentMap.put(BASKETBALL_REFERENCE_TEAM_PAGE_URL, getBasketballReferenceTeamsPage());
        return getMockWebRequest(urlToDocumentMap);
    }

    public static WebRequest getBasketballReferenceStandingsPageMockWebRequest(){
        Map<String, Document> urlToDocumentMap = new LinkedHashMap<>();
        urlToDocumentMap.put(BASKETBALL_REFERENCE_STANDINGS_PAGE_URL, getBasketballReferenceStandingsPage());
        return getMockWebRequest(urlToDocumentMap);
    }

    private static WebRequest getMockWebRequest(Map<String, Document> urlToDocumentMap){
        try {
            WebRequest webRequest = mock(WebRequest.class);
            for(String url : urlToDocumentMap.keySet()){
                when(webRequest.getPageAsDocument(url)).thenReturn(urlToDocumentMap.get(url));
            }

            return webRequest;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
