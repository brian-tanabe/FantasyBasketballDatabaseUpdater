package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.scrapers.NbaTeamSportsVuScraper;
import com.btanabe2.fbdu.dp.models.NbaTeamSportsVuModel;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.getSportsVuTeamsPageMockWebRequest;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by BTanabe on 11/21/2014.
 */
public class NbaTeamSportsVuScraperTests {
    private static List<NbaTeamSportsVuModel> nbaTeams;

    @BeforeClass
    public static void setup() {
        try {
            NbaTeamSportsVuScraper scraper = new NbaTeamSportsVuScraper(getSportsVuTeamsPageMockWebRequest());
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
}
