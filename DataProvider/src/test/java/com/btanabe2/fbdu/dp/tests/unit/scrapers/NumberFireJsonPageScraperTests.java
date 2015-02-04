package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.models.NumberFireNbaTeamModel;
import com.btanabe2.fbdu.dp.data.scrapers.NumberFireJsonPageScraper;
import com.btanabe2.fbdu.dp.fixtures.NumberFirePageFixture;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by BTanabe on 11/11/2014.
 */
public class NumberFireJsonPageScraperTests {

    @Test
    public void shouldBeAbleToFindTwoHundredNineForwardsInTheNumberFireRemainingSeasonProjectionsPage() {
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playersAttributes = scraper.getProjectionAttributeMapList(NumberFirePageFixture.getNumberFireRemainingSeasonGuardsProjectionsPageDocument());
        assertEquals("Did not find the correct number of forwards in the remaining season projections", 209, playersAttributes.size());
    }

    @Test
    public void shouldBeAbleToFindTwoHundredFiftyGuardsInTheNumberFireRemainingSeasonProjectionsPage() {
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playersAttributes = scraper.getProjectionAttributeMapList(NumberFirePageFixture.getNumberFireRemainingSeasonForwardsProjectionsPageDocument());
        assertEquals("Did not find the correct number of guards in the remaining season projections", 250, playersAttributes.size());
    }

    @Test
    public void shouldBeAbleToFindOneHundredTwentySixCentersInTheNumberFireRemainingSeasonProjectionsPage() {
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playersAttributes = scraper.getProjectionAttributeMapList(NumberFirePageFixture.getNumberFireRemainingSeasonCentersProjectionsPageDocument());
        assertEquals("Did not find the correct number of centers in the remaining season projections", 126, playersAttributes.size());
    }

    @Test
    public void shouldBeAbleToFindOneHundredFortyNinePlayersInTheNumberFireDailyProjectionsPage() {
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playersAttributes = scraper.getProjectionAttributeMapList(NumberFirePageFixture.getNumberFireDailyProjectionsPageDocument());
        assertEquals("Did not find the correct number of players in the daily projections", 149, playersAttributes.size());
    }

    @Test
    public void shouldBeAbleToParseRandomForwardsRemainingSeasonProjectionsCorrectly() {
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playersAttributes = scraper.getProjectionAttributeMapList(NumberFirePageFixture.getNumberFireDailyProjectionsPageDocument());
        Map<String, String> playerProjections = playersAttributes.stream().filter(p -> p.values().contains("Pau Gasol")).limit(1).collect(Collectors.toList()).get(0);

        assertEquals("171", playerProjections.get("id"));
        assertEquals("Pau Gasol", playerProjections.get("name"));
        assertEquals("1980-07-06", playerProjections.get("dob"));
        assertEquals("996", playerProjections.get("espn_id"));
        assertEquals("3513", playerProjections.get("yahoo_id"));
        assertEquals("10", playerProjections.get("experience"));
    }

    @Test
    public void shouldBeAbleToParseRandomForwardsDailyProjectionsCorrectly() {
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playersAttributes = scraper.getProjectionAttributeMapList(NumberFirePageFixture.getNumberFireDailyProjectionsPageDocument());
        Map<String, String> playerProjections = playersAttributes.stream().filter(p -> p.values().contains("Pau Gasol")).limit(1).collect(Collectors.toList()).get(0);

        assertEquals("171", playerProjections.get("id"));
        assertEquals("Pau Gasol", playerProjections.get("name"));
        assertEquals("1980-07-06", playerProjections.get("dob"));
        assertEquals("996", playerProjections.get("espn_id"));
        assertEquals("3513", playerProjections.get("yahoo_id"));
        assertEquals("10", playerProjections.get("experience"));
    }

    @Test
    public void shouldBeAbleToFindThirtyNbaTeams() {
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<NumberFireNbaTeamModel> nbaTeams = scraper.getNumberFireNbaTeamModels(NumberFirePageFixture.getNumberFireRankingsPageDocument());
        assertEquals(30, nbaTeams.size());
    }

    @Test
    public void shouldScrapeTheCorrectInformationForTheDenverNuggets() {
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        NumberFireNbaTeamModel nuggets = scraper.getNumberFireNbaTeamModels(NumberFirePageFixture.getNumberFireRankingsPageDocument()).stream().filter(t -> t.getAbbreviation().equalsIgnoreCase("DEN")).collect(Collectors.toList()).get(0);

        assertNotNull("The 'Denver Nuggets' object was not found", nuggets);
        assertEquals("Did not parse the Nugget's NumberFire ID properly", 7, nuggets.getNumberFireId());
        assertEquals("Did not parse the Nugget's ESPN ID properly", 7, nuggets.getEspnId());
    }
}
