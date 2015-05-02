package com.btanabe2.fbdu.dp.tests.unit.web;

import com.btanabe2.fbdu.dp.web.SecureWebRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.IOException;

import static com.btanabe2.fbdu.dp.web.WebConstants.ESPN_FANTASY_BASKETBALL_HOMEPAGE;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertTrue;

/**
 * Created by Brian on 5/1/15.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring-configuration/application.xml")
public class SecureWebRequestTests {
    @Autowired
    private SecureWebRequest webRequest;

    @Test
    public void shouldBeAbleToDownloadPagesBehindTheEspnSecurityWall() {
        try {

            assertTrue("Did not find any links to team pages.", webRequest.getPageAsDocument(ESPN_FANTASY_BASKETBALL_HOMEPAGE).select("a.leagueoffice-link[href^=http://games.espn.go.com/fba/leagueoffice?leagueId=]").size() > 0);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to download the ESPN fantasy basketball home page");
        }
    }
}
