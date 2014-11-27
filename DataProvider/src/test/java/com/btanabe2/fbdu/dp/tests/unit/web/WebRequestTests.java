package com.btanabe2.fbdu.dp.tests.unit.web;

import com.btanabe2.fbdu.dp.web.WebRequest;
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

    @Test
    public void shouldBeAbleToDownloadPlayerProfile(){
        try {
            assertTrue("Downloaded page was empty", new WebRequest().getPage("http://stats.nba.com/stats/commonplayerinfo?PlayerID=203112").length() > 0);
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to download page");
        }
    }

    @Test
    public void shouldBeAbleToMakeMultipleRequestsToDifferentDomains(){
        try {
            WebRequest webRequest = new WebRequest();
            String sportsVuPageString = webRequest.getPage(SPORTS_VU_ALL_PLAYERS_URL);
            String numberFirePage = webRequest.getPage(NUMBER_FIRE_REMAINING_PROJECTIONS_FORWARDS_URL);

            assertTrue("First downloaded page was empty", sportsVuPageString.length() > 0);
            assertTrue("Second downloaded page was empty", numberFirePage.length() > 0);
        } catch (Exception ex){
            ex.printStackTrace();
            fail("Failed to download pages");
        }
    }
}
