package com.btanabe2.fbdu.dp.tests.integration;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dp.data.providers.NbaTeamProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
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
    public static void setup() {
        try {
            nbaTeams = NbaTeamProvider.getAllNbaTeamEntities(getSportsVuTeamsPageMockWebRequest());
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to parse input JSON files");
        }
    }

    @Test
    public void shouldBeAbleToFindThirtyTeams() {
        assertEquals("Did not find 30 teams", 30, nbaTeams.size());
    }

    @Test
    public void shouldNotProduceAnyTeamsWithZeroedIds() {
        List<NbaTeamEntity> teamsWithoutIds = nbaTeams.stream().filter(t -> t.getId() == 0).collect(Collectors.toList());
        assertEquals(0, teamsWithoutIds.size());
    }

    @Test
    public void shouldNotProduceAnyTeamsWithZeroedEspnIds() {
        List<NbaTeamEntity> teamsWithoutIds = nbaTeams.stream().filter(t -> t.getEspnId() == 0).collect(Collectors.toList());
        assertEquals(0, teamsWithoutIds.size());
    }

    @Test
    public void shouldNotProduceAnyTeamsWithZeroedNumberFireIds() {
        List<NbaTeamEntity> teamsWithoutIds = nbaTeams.stream().filter(t -> t.getNumberFireId() == 0).collect(Collectors.toList());
        assertEquals(0, teamsWithoutIds.size());
    }

    @Test
    public void allIdsShouldBeUnique() {
        Set<Integer> nbaTeamIds = new HashSet<>(nbaTeams.size());
        nbaTeams.stream().filter(nbaTeam -> !nbaTeamIds.add(nbaTeam.getId())).forEach(nbaTeam -> fail(String.format("Attempting to insert ID[%d] more than once", nbaTeam.getId())));
        assertEquals("The proper number of unique IDs were not generated", nbaTeams.size(), nbaTeamIds.size());
    }

    @Test
    public void allNumberFireIdsShouldBeUnique() {
        Set<Integer> nbaTeamNumberFireIds = new HashSet<>(nbaTeams.size());
        nbaTeams.stream().filter(nbaTeam -> !nbaTeamNumberFireIds.add(nbaTeam.getNumberFireId())).forEach(nbaTeam -> fail(String.format("Attempting to insert NUMBER_FIRE_ID[%d] more than once", nbaTeam.getNumberFireId())));
        assertEquals("The proper number of unique IDs were not generated", nbaTeams.size(), nbaTeamNumberFireIds.size());
    }
}
