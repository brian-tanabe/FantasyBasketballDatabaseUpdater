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
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.getEspnFantasyIdToStandardIdProviderMockWebRequest;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 2/9/15.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TimedPositionEligibilityProviderTests {
    private static SecureWebRequest webRequest;
    private List<PlayerBiographyEntity> playerBiographyEntityList;
    private Map<Integer, Integer> idMap;

//    @Rule
//    public Timeout globalTimeout = new Timeout(60 * 1000); // 1 minute

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

    @Test
    public void aShouldBeAbleToGetAllPlayerBiographyEntityObjectsInLessThanOneMinute() {
        try {
            playerBiographyEntityList = new PlayerBiographyProvider(webRequest).getAllPlayers(NbaTeamEntityFixture.getMockNbaTeams());
            assertEquals("Did not find the proper number of PlayerBiographyEntity objects", 442, playerBiographyEntityList.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to create all PlayerBiographyEntity objects");
        }
    }

    @Test
    public void bShouldBeAbleToMapAllFantasyIdsToTheirEspnIdsInLessThanOneMinute() {
        try {
            idMap = new EspnFantasyIdToStandardIdProvider(getEspnFantasyIdToStandardIdProviderMockWebRequest()).getFantasyIdMappedToNormalIdMap();
            assertEquals("Did not map the proper number of IDs", 446, idMap.size());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to map all fantasy IDs to their ESPN IDs");
        }
    }

    @Test
    public void cShouldBeAbleToDetermineAllPlayerPositionEligibilitiesUnderOneMinute() {
        try {
            assertEquals(573, new PositionEligibilityProvider(webRequest).getPlayerPositionEligibility(playerBiographyEntityList, NbaPositionProvider.getAllPositions(), idMap).size());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to provider player position eligibilities");
        }
    }
}
