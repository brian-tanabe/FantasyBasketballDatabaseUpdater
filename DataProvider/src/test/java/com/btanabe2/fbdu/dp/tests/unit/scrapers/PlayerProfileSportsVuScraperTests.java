package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.fixtures.NbaTeamEntityFixture;
import com.btanabe2.fbdu.dp.stats.scrapers.PlayerProfileSportsVuScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.web.WebConstants.SPORTS_VU_ALL_PLAYERS_URL;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by brian on 11/19/14.
 */
public class PlayerProfileSportsVuScraperTests {
    private static PlayerProfileSportsVuScraper scraper;

    @BeforeClass
    public static void setup(){
        try {
            WebRequest mockWebRequest = mock(WebRequest.class);
            when(mockWebRequest.getPage(SPORTS_VU_ALL_PLAYERS_URL)).thenReturn(FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-sportsvu-pages/nba-commonallplayers.json"), Charset.forName("UTF8")));
            when(mockWebRequest.getPage(any(String.class))).thenReturn(FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-sportsvu-pages/playerinfo-pages/playerinfo-afflalo_aaron.json"), Charset.forName("UTF8")));

            scraper = new PlayerProfileSportsVuScraper(mockWebRequest);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to create the mock WebRequest for the PlayerProfileSportsVuScraperTests");
        }
    }

    @Test
    public void shouldBeAbleToFindFourHundredFortyNinePlayers() {
        try {
            List<PlayerBiographyEntity> allActiveNbaPlayers = scraper.scrapeForPlayerBiographies(NbaTeamEntityFixture.getMockNbaTeams());
            assertEquals("Did not find enough active players", 449, allActiveNbaPlayers.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail("JSON test files are illegally formatted or formatted in an unpredictable manner");
        }
    }

    @Test
    public void shouldParseArronAfflalosBiographyCorrectly(){
        try {
            PlayerBiographyEntity player = scraper.scrapeForPlayerBiographies(NbaTeamEntityFixture.getMockNbaTeams()).stream().filter(p -> p.getId() == 201167).limit(1).collect(Collectors.toList()).get(0);

            assertNotNull("Did not find Arron Afflalo", player);
            assertEquals("Player's name did not match", "Arron Afflalo", player.getName());
            assertEquals("Player's teamId was incorrect", 1610612743, player.getNbateamid());
            assertEquals("Player's country was incorrect", "USA", player.getCountry());
            assertEquals("UCLA", player.getSchool());
            assertEquals(201167, player.getId());
            assertEquals(7, player.getExperience());
            assertEquals(77, player.getHeight());
            assertEquals(215, player.getWeight());
            assertEquals("1985-10-15", player.getBirthday().toString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
