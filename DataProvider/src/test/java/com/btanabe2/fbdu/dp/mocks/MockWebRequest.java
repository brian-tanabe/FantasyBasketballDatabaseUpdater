package com.btanabe2.fbdu.dp.mocks;

import com.btanabe2.fbdu.dp.web.WebRequest;

import static com.btanabe2.fbdu.dp.fixtures.EspnTeamsPageFixture.getEspnTeamsPageAsDocument;
import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_TEAMS_PAGE_URL;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by brian on 11/6/14.
 */
public class MockWebRequest {

    public static WebRequest getEspnTeamsPageScraperMockWebRequest() {
        try {
            WebRequest webRequest = mock(WebRequest.class);
            when(webRequest.getPageAsDocument(ESPN_TEAMS_PAGE_URL)).thenReturn(getEspnTeamsPageAsDocument());

            return webRequest;
        } catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }
}
