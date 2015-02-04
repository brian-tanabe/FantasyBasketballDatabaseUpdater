package com.btanabe2.fbdu.dp.tests.integration;

import com.btanabe2.fbdu.dm.models.PositionEligibilityEntity;
import com.btanabe2.fbdu.dp.data.providers.NbaPositionProvider;
import com.btanabe2.fbdu.dp.data.providers.PlayerBiographyProvider;
import com.btanabe2.fbdu.dp.data.providers.PositionEligibilityProvider;
import com.btanabe2.fbdu.dp.fixtures.NbaTeamEntityFixture;
import com.btanabe2.fbdu.dp.mocks.MockWebRequest;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static com.btanabe2.fbdu.dp.fixtures.PositionEligibilityProviderFixture.TEST_LEAGUE_ID;
import static com.btanabe2.fbdu.dp.fixtures.PositionEligibilityProviderFixture.TEST_TEAM_ID;
import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.getPositionEligibilityProviderMockSecureWebRequest;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Brian on 2/2/15.
 */
public class PositionEligibilityProviderTests {
    private static List<PositionEligibilityEntity> positionEligibilityEntityList;

    @BeforeClass
    public static void setup() {
        try {
            WebRequest webRequest = MockWebRequest.getPlayerBiographyProviderMockWebRequest();
            PlayerBiographyProvider playerBiographyProvider = new PlayerBiographyProvider(webRequest);
            positionEligibilityEntityList = new PositionEligibilityProvider(getPositionEligibilityProviderMockSecureWebRequest(), TEST_LEAGUE_ID, TEST_TEAM_ID).getPlayerPositionEligibility(playerBiographyProvider.getAllPlayers(NbaTeamEntityFixture.getMockNbaTeams()), NbaPositionProvider.getAllPositions());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to successfully create all PositionEligibilityEntity objects");
        }
    }

    @Test
    public void shouldBeAbleToCreateSixHundredSeventySevenPositionEligibilityEntities() {
        assertEquals("Did not create the proper number of PositionEligibilityEntities", 677, positionEligibilityEntityList.size());
    }

    @Test
    public void shouldBeAbleToFindPositionEligibilitiesForFourHundredFortyThreePlayers() {
        Set<Integer> playerIds = new HashSet<>(536);
        positionEligibilityEntityList.forEach(p -> playerIds.add(p.getPlayerid()));
        assertEquals("Did not find PositionEligibilityEntity objects for the proper number of players", 536, playerIds.size());
    }
}
