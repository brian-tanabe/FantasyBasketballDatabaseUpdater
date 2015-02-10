package com.btanabe2.fbdu.dp.tests.time;

import com.btanabe2.fbdu.dp.data.providers.EspnFantasyIdToStandardIdProvider;
import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.auth.EspnCredentialProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;

/**
 * Created by Brian on 2/9/15.
 */
public class TimedEspnFantasyIdToStandardIdProviderTests {
    private static SecureWebRequest webRequest;

    @BeforeClass
    public static void setup() {
        try {
            webRequest = new SecureWebRequest();
            webRequest.login(new EspnCredentialProvider());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to log into ESPN using your current credentials");
        }
    }

    // originally 13:10!
    // 5:35 when the profile page scraping was threaded
    @Test(timeout = 120000) // now: 2 minutes; goal: 15 seconds
    public void shouldBeAbleToMapAllIdsInLessThanFifteenSeconds() {
        try {
            assertTrue(new EspnFantasyIdToStandardIdProvider(webRequest).getFantasyIdMappedToNormalIdMap().size() > 0);
        } catch (Exception e) {
            e.printStackTrace();
            fail("Failed to map all player IDs");
        }
    }
}
