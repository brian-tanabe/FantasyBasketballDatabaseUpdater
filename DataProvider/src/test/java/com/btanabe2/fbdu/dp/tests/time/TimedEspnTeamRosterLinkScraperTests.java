package com.btanabe2.fbdu.dp.tests.time;

import com.btanabe2.fbdu.dp.data.scrapers.EspnTeamsRosterLinkScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.junit.Rule;
import org.junit.rules.Timeout;

import java.io.IOException;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_TEAMS_PAGE_URL;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Brian on 2/9/15.
 */
public class TimedEspnTeamRosterLinkScraperTests {

    @Rule
    public Timeout globalTimeout = new Timeout(2 * 1000);   // 1 second

    //    @Test
    public void shouldBeAbleToFindAllTeamRosterLinksInLessThanTwoSeconds() {
        try {
            assertEquals("Did not find 30 team's roster links", 30, EspnTeamsRosterLinkScraper.getTeamRosterPageLinks(new WebRequest().getPageAsDocument(ESPN_TEAMS_PAGE_URL)).size());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to scrape ESPN for team roster links");
        }
    }
}
