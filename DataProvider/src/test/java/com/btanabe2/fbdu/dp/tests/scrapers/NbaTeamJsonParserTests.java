package com.btanabe2.fbdu.dp.tests.scrapers;

import com.btanabe2.fbdu.dm.models.NbaTeamEntity;
import com.btanabe2.fbdu.dp.scrapers.NbaTeamJsonParser;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/6/14.
 */
public class NbaTeamJsonParserTests {
    private static List<NbaTeamEntity> nbaTeams;

    @BeforeClass
    public static void setup(){
        try {
            nbaTeams = NbaTeamJsonParser.getAllNbaTeams();
        } catch (Exception ex){
            ex.printStackTrace();
            fail("Failed to parse the NBA teams JSON file");
        }
    }

    @Test
    public void shouldBeAbleToFindThirtyTeams(){
        assertEquals("Did not find 30 teams", 30, nbaTeams.size());
    }

    @Test
    public void shouldBeAbleToFindTheCeltics(){
        String teamName = "Boston Celtics";

        NbaTeamEntity team = nbaTeams.stream().filter(t -> t.getName().equals(teamName)).findFirst().get();
        assertNotNull(String.format("Did not find \"%s\"", teamName), team);
    }


}
