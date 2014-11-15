package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.leagues.scrapers.EspnLeagueIdScraper;
import com.btanabe2.fbdu.dp.mocks.MockWebRequest;
import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.auth.EspnCredentialProvider;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_FANTASY_BASKETBALL_HOMEPAGE;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/14/14.
 */
public class EspnLeagueIdScraperTests {

    @Test
    public void shouldBeAbleToFindOneLeagueLink() {
        try {
            SecureWebRequest webRequest = MockWebRequest.getEspnLeagueIdScraperMockWebRequest();
            EspnLeagueIdScraper scraper = new EspnLeagueIdScraper();
            List<String> leaguePageUrls = scraper.findFantasyLeagueHomePageUrls(webRequest, new EspnCredentialProvider(), ESPN_FANTASY_BASKETBALL_HOMEPAGE);
            assertEquals("Did not find the proper number of league home pages", 1, leaguePageUrls.size());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to perform the EspnLeagueIdScraperTests::shouldBeAbleToFindONeLeagueLink()");
        }
    }

    @Test
    public void shouldNotFindLinksFromOldSeason(){
        try {
            SecureWebRequest webRequest = MockWebRequest.getEspnLeagueIdScraperMockWebRequest();
            EspnLeagueIdScraper scraper = new EspnLeagueIdScraper();
            List<String> leaguePageUrls = scraper.findFantasyLeagueHomePageUrls(webRequest, new EspnCredentialProvider(), ESPN_FANTASY_BASKETBALL_HOMEPAGE);
            assertFalse("Found links it should not have", leaguePageUrls.contains("http://games.espn.go.com/fba/leagueoffice?leagueId=27015&teamId=1&seasonId=2014"));
            assertFalse("Found links it should not have", leaguePageUrls.contains("http://games.espn.go.com/fba/leagueoffice?leagueId=27015&teamId=1&seasonId=2014"));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to perform the EspnLeagueIdScraperTests::shouldNotFindLinksFromOldSeason()");
        }
    }

    @Test
    public void shouldParseTheCurrentSeasonLinksCorrectly(){
        try {
            SecureWebRequest webRequest = MockWebRequest.getEspnLeagueIdScraperMockWebRequest();
            EspnLeagueIdScraper scraper = new EspnLeagueIdScraper();
            List<String> leaguePageUrls = scraper.findFantasyLeagueHomePageUrls(webRequest, new EspnCredentialProvider(), ESPN_FANTASY_BASKETBALL_HOMEPAGE);
            assertEquals("Did not find the correct links", "http://games.espn.go.com/fba/leagueoffice?leagueId=233928&teamId=5&seasonId=2015", leaguePageUrls.get(0));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to perform the EspnLeagueIdScraperTests::shouldParseTheCurrentSeasonLinksCorrectly()");
        }
    }
}
