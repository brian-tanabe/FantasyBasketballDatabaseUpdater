package com.btanabe2.fbdu.dp.tests.integration;

import com.btanabe2.fbdu.dm.models.PositionEligibilityEntity;
import com.btanabe2.fbdu.dp.fixtures.NbaTeamEntityFixture;
import com.btanabe2.fbdu.dp.leagues.providers.NbaPositionProvider;
import com.btanabe2.fbdu.dp.mocks.MockWebRequest;
import com.btanabe2.fbdu.dp.stats.providers.PlayerBiographyProvider;
import com.btanabe2.fbdu.dp.stats.providers.PositionEligibilityProvider;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.aspectj.bridge.MessageUtil.fail;
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
            positionEligibilityEntityList = PositionEligibilityProvider.getPlayerPositionEligibility(playerBiographyProvider.getAllPlayers(NbaTeamEntityFixture.getMockNbaTeams()), NbaPositionProvider.getAllPositions());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to successfully create all PositionEligibilityEntity objects");
        }

    }

    @Test
    public void shouldBeAbleToCreateFourHundredFortyThreePositionEligibilityEntities() {
        assertEquals("Did not create the proper number of PositionEligibilityEntities", 443, positionEligibilityEntityList.size());
    }
}
