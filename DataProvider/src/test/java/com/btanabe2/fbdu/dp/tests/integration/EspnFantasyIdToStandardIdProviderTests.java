package com.btanabe2.fbdu.dp.tests.integration;

import com.btanabe2.fbdu.dp.data.providers.EspnFantasyIdToStandardIdProvider;
import com.btanabe2.fbdu.dp.web.auth.EspnCredentialProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.getEspnFantasyIdToStandardIdProviderMockWebRequest;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnFantasyIdToStandardIdProviderTests {
    private static Map<Integer, Integer> fantasyIdMappedToNormalIdMap;

    @BeforeClass
    public static void setup() {
        try {
            fantasyIdMappedToNormalIdMap = new EspnFantasyIdToStandardIdProvider(getEspnFantasyIdToStandardIdProviderMockWebRequest()).getFantasyIdMappedToNormalIdMap(new EspnCredentialProvider());
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to scrape ESPN player IDs using their fantasy IDs");
        }
    }

    @Test
    public void shouldBeAbleToMapFourHundredFortySixPlayersEspnIds() {
        assertEquals("Did not find the proper number of players on their team roster pages", 446, fantasyIdMappedToNormalIdMap.size());
    }

    @Test
    public void shouldBeAbleToMapPauGasolsIdsProperly() {
        assertEquals("Did not map Pau Gasol's fantasy ID and ESPN ID properly", (Integer) 996, fantasyIdMappedToNormalIdMap.get(162));
    }

    @Test
    public void shouldBeAbleToMapLaryDrewsIdsProperly() {
        assertEquals("Did not map Larry Drew II's fantasy ID and ESPN ID properly", (Integer) 2325495, fantasyIdMappedToNormalIdMap.get(1162));
    }
}
