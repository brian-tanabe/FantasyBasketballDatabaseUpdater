package com.btanabe2.fbdu.dp.tests.time;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.data.providers.EspnFantasyIdToStandardIdProvider;
import com.btanabe2.fbdu.dp.data.providers.NbaPositionProvider;
import com.btanabe2.fbdu.dp.data.providers.PlayerBiographyProvider;
import com.btanabe2.fbdu.dp.data.providers.PositionEligibilityProvider;
import com.btanabe2.fbdu.dp.fixtures.NbaTeamEntityFixture;
import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.auth.EspnCredentialProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;

/**
 * Created by brian on 2/9/15.
 */
public class TimedPositionEligibilityProviderTests {
    private static SecureWebRequest webRequest;

//    @Rule
//    public Timeout globalTimeout = new Timeout(10 * 1000); // 1 minute

    @BeforeClass
    public static void setup() {
        try {
            webRequest = new SecureWebRequest();
            webRequest.login(new EspnCredentialProvider());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to log into ESPN");
        }
    }

    // originally 11:44!
    @Test
    public void shouldBeAbleToDetermineAllPlayerPositionEligibilitiesUnderTenSeconds() {
        try {
            List<PlayerBiographyEntity> playerBiographyEntityList = new PlayerBiographyProvider(webRequest).getAllPlayers(NbaTeamEntityFixture.getMockNbaTeams());
            Map<Integer, Integer> idMap = new EspnFantasyIdToStandardIdProvider(webRequest).getFantasyIdMappedToNormalIdMap();

            assertTrue(new PositionEligibilityProvider(webRequest).getPlayerPositionEligibility(playerBiographyEntityList, NbaPositionProvider.getAllPositions(), idMap).size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to provider player position eligibilities");
        }
    }
}
