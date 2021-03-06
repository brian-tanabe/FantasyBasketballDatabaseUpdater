package com.btanabe2.fbdu.dp.tests.time;

import com.btanabe2.fbdu.dp.data.scrapers.EspnTeamsRosterLinkScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.junit.Test;

import java.io.IOException;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_TEAMS_PAGE_URL;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Brian on 2/9/15.
 */
public class TimedEspnTeamRosterLinkScraperTests {

    @Test(timeout = 2000)   // 2 seconds
    public void shouldBeAbleToFindAllTeamRosterLinksInLessThanTwoSeconds() {
        try {
            assertEquals("Did not find 30 team's roster links", 30, EspnTeamsRosterLinkScraper.getTeamRosterPageLinks(new WebRequest().getPageAsDocument(ESPN_TEAMS_PAGE_URL)).size());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to scrape ESPN for team roster links");
        }
    }
}
