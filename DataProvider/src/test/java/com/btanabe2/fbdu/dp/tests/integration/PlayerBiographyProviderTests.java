package com.btanabe2.fbdu.dp.tests.integration;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.fixtures.NbaTeamEntityFixture;
import com.btanabe2.fbdu.dp.stats.providers.PlayerBiographyProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.getPlayerBiographyProviderMockWebRequest;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Brian on 11/21/14.
 */
public class PlayerBiographyProviderTests {
    private static List<PlayerBiographyEntity> players;

    @BeforeClass
    public static void setup() {
        try {
            PlayerBiographyProvider provider = new PlayerBiographyProvider(getPlayerBiographyProviderMockWebRequest());
            players = provider.getAllPlayers(NbaTeamEntityFixture.getMockNbaTeams());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to assemble all PlayerBiographyEntity objects");
        }
    }

    @Test
    public void shouldBeAbleToFindFourHundredSeventyOnePlayers() {
        assertEquals(471, players.size());
    }

    @Test
    public void shouldBeAbleToScrapeArronAfflalosPlayerBiographyEntityCorrectly(){
        PlayerBiographyEntity player = players.stream().filter(p -> p.getName().equals("Arron Afflalo")).limit(1).collect(Collectors.toList()).get(0);

        assertNotNull("Did not find Arron Afflalo using his name as the key", player);
        assertEquals("Arron Afflalo", player.getName());
        assertEquals(203112, player.getId());
        assertEquals(1610612743, player.getNbateamid());
        assertEquals("1985-10-15", player.getBirthday().toString());
        assertEquals(7, player.getExperience());
        assertEquals(77, player.getHeight());
        assertEquals(215, player.getWeight());
        assertEquals("UCLA", player.getSchool());
        assertEquals("USA", player.getCountry());
        assertEquals(3187, player.getEspnid());
        assertEquals(123, player.getNumberfireid());
    }
}
