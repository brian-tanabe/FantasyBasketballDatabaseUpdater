package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.fixtures.NbaTeamEntityFixture;
import com.btanabe2.fbdu.dp.stats.scrapers.PlayerProfileSportsVuScraper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.getPlayerProfileSportsVuScraperMockWebRequest;
import static junit.framework.Assert.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/19/14.
 */
public class PlayerProfileSportsVuScraperTests {
    private static List<PlayerBiographyEntity> allActiveNbaPlayers;

    @BeforeClass
    public static void setup(){
        try {
            PlayerProfileSportsVuScraper scraper = new PlayerProfileSportsVuScraper(getPlayerProfileSportsVuScraperMockWebRequest());
            allActiveNbaPlayers = scraper.scrapeForPlayerBiographies(NbaTeamEntityFixture.getMockNbaTeams());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to create the mock WebRequest for the PlayerProfileSportsVuScraperTests");
        }
    }

    @Test
    public void shouldBeAbleToFindFourHundredFortyThreePlayers() {
        try {
            assertEquals("Did not find the correct number of active players", 443, allActiveNbaPlayers.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail("JSON test files are illegally formatted or formatted in an unpredictable manner");
        }
    }

    @Test
    public void shouldParseArronAfflalosBiographyCorrectly(){
        try {
            PlayerBiographyEntity player = allActiveNbaPlayers.stream().filter(p -> p.getId() == 201167).limit(1).collect(Collectors.toList()).get(0);

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
            fail("Failed to parse Aaron Afflalo's player profile correctly");
        }
    }

    @Test
    public void shouldNotIncludeInactivePlayersInTheParsedResults() {
        assertFalse("Player with an ID of 202399 is inactive and shouldn't be parsed", allActiveNbaPlayers.stream().anyMatch(p -> p.getId() == 202399));
    }
}
