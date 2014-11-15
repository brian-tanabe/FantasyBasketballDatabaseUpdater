package com.btanabe2.fbdu.dp.tests.scrapers;

import com.btanabe2.fbdu.dp.leagues.scrapers.EspnLeagueIdScraper;
import com.btanabe2.fbdu.dp.mocks.MockWebRequest;
import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/14/14.
 */
public class EspnLeagueIdScraperTests {

    @Test
    public void shouldBeAbleToFindOneLeagueLink(){
        SecureWebRequest webRequest = MockWebRequest.getEspnLeagueIdScraperMockWebRequest();
        EspnLeagueIdScraper scraper = new EspnLeagueIdScraper(webRequest);
        List<String> leaguePageUrls = scraper.findFantasyLeagueHomePageUrls();
        assertEquals("Did not find the proper number of leage home pages", 1, leaguePageUrls.size());
    }
}
