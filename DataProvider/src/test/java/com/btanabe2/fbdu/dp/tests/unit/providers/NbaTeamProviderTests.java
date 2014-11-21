package com.btanabe2.fbdu.dp.tests.unit.providers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dp.stats.providers.NbaTeamProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.getSportsVuTeamsPageMockWebRequest;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/6/14.
 */
public class NbaTeamProviderTests {
    private static List<NbaTeamEntity> nbaTeams;

    @BeforeClass
    public static void setup(){
        try {
            nbaTeams = NbaTeamProvider.getAllNbaTeamEntities(getSportsVuTeamsPageMockWebRequest());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to parse input JSON files");
        }
    }

    @Test
    public void shouldBeAbleToFindThirtyTeams(){
        assertEquals("Did not find 30 teams", 30, nbaTeams.size());
    }

    @Test
    public void shouldNotProduceAnyTeamsWithNullIds(){
        List<NbaTeamEntity> teamsWithoutIds = nbaTeams.stream().filter(t -> t.getId() == 0).collect(Collectors.toList());
        assertEquals(0, teamsWithoutIds.size());
    }
}
