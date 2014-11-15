package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.fixtures.NumberFirePageFixture;
import com.btanabe2.fbdu.dp.scrapers.NumberFireRankingScraper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.text.ParseException;
import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by BTanabe on 11/10/2014.
 */
public class NumberFireRankingsScraperTests {
    private static NumberFireRankingScraper scraper;

    @BeforeClass
    public static void setup() {
        try {
            scraper = new NumberFireRankingScraper();
            scraper.scrapeForPlayers(NumberFirePageFixture.getNumberFireRankingsPageDocument());
        } catch (ParseException e) {
            e.printStackTrace();
            fail("Failed to parse the input test file");
        }
    }

    @Test
    public void shouldBeAbleToFindOneHundredPlayers() {
        List<PlayerBiographyEntity> players = scraper.getPlayerBiographyEntitiesList();
        assertEquals("Did not find the correct number of players in the player's list", 100, players.size());
    }
}
