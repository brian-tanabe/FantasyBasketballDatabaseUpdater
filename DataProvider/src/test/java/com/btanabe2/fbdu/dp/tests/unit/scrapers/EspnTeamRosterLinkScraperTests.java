package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnTeamsRosterLinkScraper;
import com.btanabe2.fbdu.dp.helpers.FileDocumentor;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnTeamRosterLinkScraperTests {
    private static List<String> teamRosterPageLinks;

    @BeforeClass
    public static void setup() {
        teamRosterPageLinks = EspnTeamsRosterLinkScraper.getTeamRosterPageLinks(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/espn-team-pages/espn-teams-page.html"));
    }

    @Test
    public void shouldBeAbleToFindLinksToThirtyTeamsRosterPages() {
        assertEquals("Did not find links to 30 team pages", 30, teamRosterPageLinks.size());
    }

    @Test
    public void shouldBeAbleToFormatChicagosTeamRosterPageLinkProperly() {
        String chicagoRosterPageLink = teamRosterPageLinks.stream().filter(t -> t.contains("chi")).limit(1).collect(Collectors.toList()).get(0);
        assertEquals("Did not scrape the proper link for Chicago", "http://espn.go.com/nba/team/roster/_/name/chi", chicagoRosterPageLink);
    }
}
