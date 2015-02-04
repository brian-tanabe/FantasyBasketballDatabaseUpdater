package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.scrapers.NbaTeamJsonParser;
import com.btanabe2.fbdu.dp.models.NbaTeamModel;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.fail;
import static org.junit.Assert.assertEquals;

/**
 * Created by brian on 11/6/14.
 */
public class NbaTeamJsonParserTests {
    private static List<NbaTeamModel> nbaTeams;

    @BeforeClass
    public static void setup() {
        try {
            nbaTeams = NbaTeamJsonParser.getAllNbaTeams();
        } catch (Exception ex) {
            ex.printStackTrace();
            fail("Failed to parse the NBA teams JSON file");
        }
    }

    @Test
    public void shouldBeAbleToFindThirtyTeams() {
        assertEquals("Did not find 30 teams", 30, nbaTeams.size());
    }

    @Test
    public void shouldBeAbleToFindTheCeltics() {
        runTest("Boston Celtics", "Boston, MA", "BOS");
    }

    @Test
    public void shouldBeAbleToFindTheNets() {
        runTest("Brooklyn Nets", "New York, NY", "BKN");
    }

    @Test
    public void shouldBeAbleToFindTheKnicks() {
        runTest("New York Knicks", "New York, NY", "NYK");
    }

    @Test
    public void shouldBeAbleToFindPhiladelphia() {
        runTest("Philadelphia 76ers", "Philadelphia, PA", "PHI");
    }

    @Test
    public void shouldBeAbleToFindTheRaptors() {
        runTest("Toronto Raptors", "Toronto, ON", "TOR");
    }

    @Test
    public void shouldBeAbleToFindTheBulls() {
        runTest("Chicago Bulls", "Chicago, IL", "CHI");
    }

    @Test
    public void shouldBeAbleToFindTheCavaliers() {
        runTest("Cleveland Cavaliers", "Cleveland, OH", "CLE");
    }

    @Test
    public void shouldBeAbleToFindThePistons() {
        runTest("Detroit Pistons", "Detroit, MI", "DET");
    }

    @Test
    public void shouldBeAbleToFindThePacers() {
        runTest("Indiana Pacers", "Indianapolis, IN", "IND");
    }

    @Test
    public void shouldBeAbleToFindTheBucks() {
        runTest("Milwaukee Bucks", "Milwaukee, WI", "MIL");
    }

    @Test
    public void shouldBeAbleToFindTheHawks() {
        runTest("Atlanta Hawks", "Atlanta, GA", "ATL");
    }

    @Test
    public void shouldBeAbleToFindTheHornets() {
        runTest("Charlotte Hornets", "Charlotte, NC", "CHA");
    }

    @Test
    public void shouldBeAbleToFindTheHeat() {
        runTest("Miami Heat", "Miami, FL", "MIA");
    }

    @Test
    public void shouldBeAbleToFindTheMagic() {
        runTest("Orlando Magic", "Orlando, FL", "ORL");
    }

    @Test
    public void shouldBeAbleToFindTheWizards() {
        runTest("Washington Wizards", "Washington, DC", "WAS");
    }

    @Test
    public void shouldBeAbleToFindTheWarriors() {
        runTest("Golden State Warriors", "Oakland, CA", "GSW");
    }

    @Test
    public void shouldBeAbleToFindTheClippers() {
        runTest("Los Angeles Clippers", "Los Angeles, CA", "LAC");
    }

    @Test
    public void shouldBeAbleToFindTheLakers() {
        runTest("Los Angeles Lakers", "Los Angeles, CA", "LAL");
    }

    @Test
    public void shouldBeAbleToFindTheSuns() {
        runTest("Phoenix Suns", "Phoenix, AZ", "PHX");
    }

    @Test
    public void shouldBeAbleToFindTheKings() {
        runTest("Sacramento Kings", "Sacramento, CA", "SAC");
    }

    @Test
    public void shouldBeAbleToFindTheMavericks() {
        runTest("Dallas Mavericks", "Dallas, TX", "DAL");
    }

    @Test
    public void shouldBeAbleToFindTheRockets() {
        runTest("Houston Rockets", "Houston, TX", "HOU");
    }

    @Test
    public void shouldBeAbleToFindTheGrizzlies() {
        runTest("Memphis Grizzlies", "Memphis, TN", "MEM");
    }

    @Test
    public void shouldBeAbleToFindThePelicans() {
        runTest("New Orleans Pelicans", "New Orleans, LA", "NOP");
    }

    @Test
    public void shouldBeAbleToFindTheSpurs() {
        runTest("San Antonio Spurs", "San Antonio, TX", "SAS");
    }

    @Test
    public void shouldBeAbleToFindTheNuggets() {
        runTest("Denver Nuggets", "Denver, CO", "DEN");
    }

    @Test
    public void shouldBeAbleToFindTheTimberwolves() {
        runTest("Minnesota Timberwolves", "Minneapolis, MN", "MIN");
    }

    @Test
    public void shouldBeAbleToFindTheTunder() {
        runTest("Oklahoma City Thunder", "Oklahoma City, OK", "OKC");
    }

    @Test
    public void shouldBeAbleToFindTheTrailBlazers() {
        runTest("Portland Trail Blazers", "Portland, OR", "POR");
    }

    @Test
    public void shouldBeAbleToFindTheJazz() {
        runTest("Utah Jazz", "Salt Lake City, UT", "UTA");
    }

    private void runTest(String name, String location, String abbreviation) {
        NbaTeamModel teamName = nbaTeams.stream().filter(t -> t.getName().equals(name)).findFirst().get();
        NbaTeamModel teamLocation = nbaTeams.stream().filter(t -> t.getLocation().equals(location) && (t.getLocation().equals(abbreviation) || t.getName().equals(name))).findFirst().get();
        NbaTeamModel teamAbbreviation = nbaTeams.stream().filter(t -> t.getAbbreviation().equals(abbreviation)).findFirst().get();

        assertEquals(name, teamName.getName());
        assertEquals(location, teamLocation.getLocation());
        assertEquals(abbreviation, teamAbbreviation.getAbbreviation());
    }
}
