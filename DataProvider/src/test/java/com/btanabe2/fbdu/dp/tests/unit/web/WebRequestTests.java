package com.btanabe2.fbdu.dp.tests.unit.web;

import com.btanabe2.fbdu.dp.web.WebRequest;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;

import static com.btanabe2.fbdu.dp.web.WebConstants.NUMBER_FIRE_REMAINING_PROJECTIONS_FORWARDS_URL;
import static com.btanabe2.fbdu.dp.web.WebConstants.SPORTS_VU_ALL_PLAYERS_URL;
import static junit.framework.Assert.assertTrue;
import static junit.framework.TestCase.fail;

/**
 * Created by Brian on 11/27/14.
 */
public class WebRequestTests {
    private static WebRequest webRequest;

    @BeforeClass
    public static void setup() {
        webRequest = new WebRequest();
    }

    @Test
    public void shouldBeAbleToDownloadPlayerProfile(){
        try {
            assertTrue("Downloaded page was empty", webRequest.getPage("http://stats.nba.com/stats/commonplayerinfo?PlayerID=203112").length() > 0);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to download page");
        }
    }

    @Test
    public void shouldBeAbleToMakeMultipleRequestsToDifferentDomains(){
        try {
            assertTrue("First downloaded page was empty", webRequest.getPage(SPORTS_VU_ALL_PLAYERS_URL).length() > 0);
            assertTrue("Second downloaded page was empty", webRequest.getPage(NUMBER_FIRE_REMAINING_PROJECTIONS_FORWARDS_URL).length() > 0);
        } catch (Exception ex){
            ex.printStackTrace();
            fail("Failed to download pages");
        }
    }
}
