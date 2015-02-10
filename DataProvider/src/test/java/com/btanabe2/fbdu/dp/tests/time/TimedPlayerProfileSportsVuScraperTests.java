package com.btanabe2.fbdu.dp.tests.time;

import com.btanabe2.fbdu.dp.data.scrapers.sportsvu.PlayerProfileSportsVuScraper;
import com.btanabe2.fbdu.dp.fixtures.NbaTeamEntityFixture;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.Timeout;

import static org.jsoup.helper.Validate.fail;
import static org.junit.Assert.assertTrue;

/**
 * Created by brian on 2/9/15.
 */
public class TimedPlayerProfileSportsVuScraperTests {

    @Rule
    public Timeout globalTimeout = new Timeout(5 * 1000); // 5 seconds

    @Test
    public void shouldBeAbleToGetAllPlayerInLessThanFifteenSeconds() {
        try {
            PlayerProfileSportsVuScraper scraper = new PlayerProfileSportsVuScraper(new WebRequest());
            assertTrue("Did not find the correct number of active players", scraper.scrapeForPlayerBiographies(NbaTeamEntityFixture.getMockNbaTeams()).size() > 440);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to get all PlayerBiographyEntity objects");
        }
    }
}
