package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnLeagueIdAndTeamIdScraper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;

import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.getEspnLeagueIdAndTeamIdScraperMockWebRequest;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/14/14.
 */
public class EspnLeagueIdAndTeamIdScraperTests {
    private static List<String> leaguePageUrls;

    @BeforeClass
    public static void setup() {
        try {
            leaguePageUrls = EspnLeagueIdAndTeamIdScraper.findFantasyLeagueHomePageUrls(getEspnLeagueIdAndTeamIdScraperMockWebRequest());
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
            assertEquals("Did not find the proper ESPN fantasy league ID", 233928, EspnLeagueIdAndTeamIdScraper.findCurrentSeasonFantasyLeagueId(getEspnLeagueIdAndTeamIdScraperMockWebRequest(), "Hoop Dreams"));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to parse ESPN for the current season fantasy team links");
        }
    }

    @Test
    public void shouldBeAbleToDetermineTheFirstLeagueIdAsAnInteger() {
        try {
            assertEquals("Did not find the proper ESPN fantasy league ID", 233928, EspnLeagueIdAndTeamIdScraper.findFirstCurrentSeasonFantasyLeagueId(getEspnLeagueIdAndTeamIdScraperMockWebRequest()));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to parse ESPN for the current season fantasy team links");
        }
    }

    @Test
    public void shouldBeAbleToDetermineUsersTeamIdAsAnInteger() {
        try {
            assertEquals("Did not find the proper user's ESPN team ID", 5, EspnLeagueIdAndTeamIdScraper.getUsersTeamId(getEspnLeagueIdAndTeamIdScraperMockWebRequest(), 233928));
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to parse for the user's team ID");
        }
    }
}
