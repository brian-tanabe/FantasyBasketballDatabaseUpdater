package com.btanabe2.fbdu.dp.tests.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.fixtures.NumberFirePageFixture;
import com.btanabe2.fbdu.dp.scrapers.NumberFireRankingScraper;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by BTanabe on 11/10/2014.
 */
public class NumberFireRankingsScraperTests {

    @Test
    public void shouldBeAbleToFindTwoHundredFiftyPlayers(){
        NumberFireRankingScraper scraper = new NumberFireRankingScraper(NumberFirePageFixture.getNumberFireRankingsPageDocument());
        List<PlayerBiographyEntity> players = scraper.getPlayerBiographyEntitiesList();
        assertEquals("Did not find the correct number of players in the player's list", 250, players.size());
    }
}
