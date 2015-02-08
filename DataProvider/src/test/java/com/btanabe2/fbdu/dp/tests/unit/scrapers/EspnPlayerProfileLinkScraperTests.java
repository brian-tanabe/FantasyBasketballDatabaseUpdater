package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnPlayerProfileLinkScraper;
import com.btanabe2.fbdu.dp.helpers.FileDocumentor;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnPlayerProfileLinkScraperTests {
    private static List<String> playerProfilePageLinks;

    @BeforeClass
    public static void setup() {
        playerProfilePageLinks = EspnPlayerProfileLinkScraper.getPlayerProfileLinks(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/espn-team-pages/espn-team-roster-page-chi.html"));
    }

    @Test
    public void shouldBeAbleToFindLinksToTwelvePlayers() {
        assertEquals("Did not find the proper number of links on the Chicago roster page", 14, playerProfilePageLinks.size());
    }

    @Test
    public void shouldBeAbleToFindTheProperLinkForDerrickRose() {
        assertEquals("Did not find the proper link for Derrick Rose", "http://espn.go.com/nba/player/_/id/3456/derrick-rose", playerProfilePageLinks.stream().filter(p -> p.contains("derrick-rose")).limit(1).collect(Collectors.toList()).get(0));
    }
}
