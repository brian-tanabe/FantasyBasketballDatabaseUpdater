package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnLeagueIdScraper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.getEspnLeagueIdScraperMockWebRequest;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/14/14.
 */
public class EspnLeagueIdScraperTests {
    private static List<String> leaguePageUrls;
    private static EspnLeagueIdScraper scraper;

    @BeforeClass
    public static void setup() {
        try {
            scraper = new EspnLeagueIdScraper();
            leaguePageUrls = scraper.findFantasyLeagueHomePageUrls(getEspnLeagueIdScraperMockWebRequest());
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
        try {
            assertEquals("Did not find the proper ESPN fantasy league ID", 233928, scraper.findCurrentSeasonFantasyLeagueId(getEspnLeagueIdScraperMockWebRequest(), "Hoop Dreams"));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to parse ESPN for the current season fantasy team links");
        }
    }

    @Test
    public void shouldBeAbleToDetermineTheFirstLeagueIdAsAnInteger() {
        try {
            assertEquals("Did not find the proper ESPN fantasy league ID", 233928, scraper.findFirstCurrentSeasonFantasyLeagueId(getEspnLeagueIdScraperMockWebRequest()));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to parse ESPN for the current season fantasy team links");
        }
    }
}
