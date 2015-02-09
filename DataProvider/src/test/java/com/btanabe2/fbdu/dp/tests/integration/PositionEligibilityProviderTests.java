package com.btanabe2.fbdu.dp.tests.integration;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dm.models.PositionEligibilityEntity;
import com.btanabe2.fbdu.dp.data.providers.EspnFantasyIdToStandardIdProvider;
import com.btanabe2.fbdu.dp.data.providers.NbaPositionProvider;
import com.btanabe2.fbdu.dp.data.providers.PlayerBiographyProvider;
import com.btanabe2.fbdu.dp.data.providers.PositionEligibilityProvider;
import com.btanabe2.fbdu.dp.fixtures.NbaTeamEntityFixture;
import com.btanabe2.fbdu.dp.web.auth.EspnCredentialProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.fixtures.PositionEligibilityProviderFixture.TEST_LEAGUE_ID;
import static com.btanabe2.fbdu.dp.fixtures.PositionEligibilityProviderFixture.TEST_TEAM_ID;
import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.*;
import static junit.framework.Assert.assertTrue;
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
            EspnFantasyIdToStandardIdProvider idMapProvider = new EspnFantasyIdToStandardIdProvider(getEspnFantasyIdToStandardIdProviderMockWebRequest());
            Map<Integer, Integer> playerFantasyIdsToEspnIdMap = idMapProvider.getFantasyIdMappedToNormalIdMap(new EspnCredentialProvider());

            PlayerBiographyProvider playerBiographyProvider = new PlayerBiographyProvider(getPlayerBiographyProviderMockWebRequest());
            List<PlayerBiographyEntity> playerBiographyEntityList = playerBiographyProvider.getAllPlayers(NbaTeamEntityFixture.getMockNbaTeams());
            positionEligibilityEntityList = new PositionEligibilityProvider(getPositionEligibilityProviderMockSecureWebRequest(), TEST_LEAGUE_ID, TEST_TEAM_ID).getPlayerPositionEligibility(playerBiographyEntityList, NbaPositionProvider.getAllPositions(), playerFantasyIdsToEspnIdMap);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to successfully create all PositionEligibilityEntity objects");
        }
    }

    @Test
    public void shouldBeAbleToCreateFiveHundredSeventyThreePositionEligibilityEntities() {
        assertEquals("Did not create the proper number of PositionEligibilityEntities", 573, positionEligibilityEntityList.size());
    }

    @Test
    public void shouldBeAbleToFindPositionEligibilitiesForFourHundredFortyPlayers() {
        Set<Integer> playerIds = new HashSet<>(536);
        positionEligibilityEntityList.forEach(p -> playerIds.add(p.getPlayerid()));
        assertEquals("Did not find PositionEligibilityEntity objects for the proper number of players", 440, playerIds.size());
    }

    // fantasy: 162
    @Test
    public void shouldBeAbleToFindPositionEligibilitiesForPauGasol() {
        List<PositionEligibilityEntity> positions = positionEligibilityEntityList.stream().filter(p -> p.getPlayerid() == 996).collect(Collectors.toList());
        assertEquals("Pau Gasol should have 2 eligible positions", 2, positions.size());
        assertTrue("Pau Gasol should be PF eligible", positions.stream().anyMatch(p -> p.getPositionid() == 4));
        assertTrue("Pau Gasol should be C eligible", positions.stream().anyMatch(p -> p.getPositionid() == 5));
    }
}
