package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.models.NbaTeamSportsVuModel;
import com.btanabe2.fbdu.dp.stats.scrapers.NbaTeamSportsVuScraper;
import com.btanabe2.fbdu.dp.web.WebRequest;
import org.apache.commons.io.FileUtils;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.web.WebConstants.SPORTS_VU_NBA_TEAM_INFO_URL;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by BTanabe on 11/21/2014.
 */
public class NbaTeamSportsVuScraperTests {
    private static List<NbaTeamSportsVuModel> nbaTeams;

    @BeforeClass
    public static void setup() {
        try {
            WebRequest webRequest = mock(WebRequest.class);
            when(webRequest.getPage(SPORTS_VU_NBA_TEAM_INFO_URL)).thenReturn(FileUtils.readFileToString(new File("./DataProvider/src/test/resources/webpages/nba-sportsvu-pages/nba-commonteamyear.json")));
            NbaTeamSportsVuScraper scraper = new NbaTeamSportsVuScraper(webRequest);
            nbaTeams = scraper.getAllNbaTeams();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to load test JSON file");
        }
    }

    @Test
    public void shouldFindThirtyTeams() {
        assertEquals("Did not find the proper number of teams", 30, nbaTeams.size());
    }

    @Test
    public void shouldParseChicagoCorrectly() {
        NbaTeamSportsVuModel team = nbaTeams.stream().filter(t -> t.getId() == 1610612741).limit(1).collect(Collectors.toList()).get(0);

        assertNotNull("Did not find Chicago using it's ID as a key", team);
        assertEquals("CHI", team.getAbbreviation());
        assertEquals(1610612741, team.getId());
    }

    @Test
    public void pleaseDeleteMe(){
        nbaTeams.forEach(t -> System.out.println(t.toString()));
    }
}
