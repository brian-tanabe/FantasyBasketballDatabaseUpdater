package com.btanabe2.fbdu.dp.tests.unit.web;

import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import com.btanabe2.fbdu.dp.web.auth.CredentialProviderI;
import com.btanabe2.fbdu.dp.web.auth.EspnCredentialProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_FANTASY_BASKETBALL_HOMEPAGE;
import static org.aspectj.bridge.MessageUtil.fail;
import static org.junit.Assert.assertTrue;

/**
 * Created by Brian on 2/4/15.
 */
public class SecureWebRequestTests {
    private static SecureWebRequest webRequest;

    @BeforeClass
    public static void setup() {
        CredentialProviderI credentials = new EspnCredentialProvider();
        try {
            webRequest = new SecureWebRequest();
            webRequest.login(credentials);
        } catch (IOException e) {
            e.printStackTrace();
            fail(String.format("Failed to log into ESPN with your current credentials: username=[%s]; password=[%s]; loginUrl=[%s]", credentials.getUserName(), credentials.getPassword(), credentials.getLoginUrl()));
        }
    }

    @Test
    public void shouldBeAbleToDownloadPagesBehindTheEspnSecurityWall() {
        try {
            fail("Make sure this works when you have Internet");
            assertTrue("Did not find any links to team pages.", webRequest.getPageAsDocument(ESPN_FANTASY_BASKETBALL_HOMEPAGE).select("a.clubhouse-link[href^=http://games.espn.go.com/fba/clubhouse?leagueId=]").size() > 0);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to download the ESPN fantasy basketball home page");
        }
    }
}
