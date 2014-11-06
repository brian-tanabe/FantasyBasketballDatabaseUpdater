package com.btanabe2.fbdu.dp.tests.scrapers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dp.scrapers.NbaTeamScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.junit.Test;

import java.util.List;

import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.getEspnTeamsPageScraperMockWebRequest;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/6/14.
 */
public class NbaTeamScraperTests {

    @Test
    public void shouldBeAbleToFindThirtyTeams(){
        try {
            WebRequest webRequest = getEspnTeamsPageScraperMockWebRequest();
            List<NbaTeamEntity> nbaTeams = NbaTeamScraper.getAllNbaTeams(webRequest);
            assertEquals("Did not find 30 teams", 30, nbaTeams.size());
        } catch (Exception ex){
            ex.printStackTrace();
            fail("Failed to scrape ESPN for all NBA teams properly");
        }
    }
}
