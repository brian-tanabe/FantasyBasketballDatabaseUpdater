package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnLeagueIdScraper;
import com.btanabe2.fbdu.dp.mocks.MockWebRequest;
import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/14/14.
 */
public class EspnLeagueIdScraperTests {
    private static List<String> leaguePageUrls;
    private static SecureWebRequest webRequest;
    private static EspnLeagueIdScraper scraper;

    @BeforeClass
    public static void setup() {
        try {
            webRequest = MockWebRequest.getEspnLeagueIdScraperMockWebRequest();
            scraper = new EspnLeagueIdScraper();
            leaguePageUrls = scraper.findFantasyLeagueHomePageUrls(webRequest);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to create the mock SecureWebRequest required by this test class");
        }
    }

    @Test
    public void shouldBeAbleToFindOneLeagueLink() {
        assertEquals("Did not find the proper number of league home pages", 1, leaguePageUrls.size());
    }

    @Test
    public void shouldNotFindLinksFromOldSeason() {
        assertFalse("Found links it should not have", leaguePageUrls.contains("http://games.espn.go.com/fba/leagueoffice?leagueId=27015&teamId=1&seasonId=2014"));
        assertFalse("Found links it should not have", leaguePageUrls.contains("http://games.espn.go.com/fba/leagueoffice?leagueId=27015&teamId=1&seasonId=2014"));
    }

    @Test
    public void shouldParseTheCurrentSeasonLinksCorrectly() {
        assertEquals("Did not find the correct links", "http://games.espn.go.com/fba/leagueoffice?leagueId=233928&teamId=5&seasonId=2015", leaguePageUrls.get(0));
    }

    @Test
    public void shouldBeAbleToDetermineTheLeagueIdAsAnIntegerIfGivenALeagueNameHint() {
        scraper.findCurrentSeasonFanasyLeagueId(webRequest, "Hoop Dreams");
    }
}
