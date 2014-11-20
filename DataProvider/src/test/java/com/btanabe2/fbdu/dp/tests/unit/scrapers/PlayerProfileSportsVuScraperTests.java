package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.stats.scrapers.PlayerProfileSportsVuScraper;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/19/14.
 */
public class PlayerProfileSportsVuScraperTests {

    @Test
    public void shouldBeAbleToFindFourHundredFortyNinePlayers() {
        try {
            PlayerProfileSportsVuScraper scraper = new PlayerProfileSportsVuScraper();
            List<Map<String, String>> allActiveNbaPlayers = scraper.getAllActivePlayersNameIdAndExperience(FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-sportsvu-pages/nba-commonallplayers.json"), Charset.forName("UTF8")));
            assertEquals("Did not find enough active players", 449, allActiveNbaPlayers.size());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to read input test files");
        }
    }
}
