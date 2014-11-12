package com.btanabe2.fbdu.dp.tests.scrapers;

import com.btanabe2.fbdu.dp.fixtures.NumberFirePageFixture;
import com.btanabe2.fbdu.dp.scrapers.NumberFireJsonPageScraper;
import org.junit.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

/**
 * Created by BTanabe on 11/11/2014.
 */
public class NumberFireJsonPageScraperTests {

    @Test
    public void shouldBeAbleToFindTwoHundredNineForwardsInTheNumberFireRemainingSeasonProjectionsPage(){
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playersAttributes = scraper.getProjectionAttributeMapList(NumberFirePageFixture.getNumberFireRemainingSeasonGuardsProjectionsPageDocument());
        assertEquals("Did not find the correct number of forwards in the remaining season projections", 209, playersAttributes.size());
    }

    @Test
    public void shouldBeAbleToFindTwoHundredFiftyGuardsInTheNumberFireRemainingSeasonProjectionsPage(){
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playersAttributes = scraper.getProjectionAttributeMapList(NumberFirePageFixture.getNumberFireRemainingSeasonForwardsProjectionsPageDocument());
        assertEquals("Did not find the correct number of guards in the remaining season projections", 250, playersAttributes.size());
    }

    @Test
    public void shouldBeAbleToFindOneHundredTwentySixCentersInTheNumberFireRemainingSeasonProjectionsPage(){
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playersAttributes = scraper.getProjectionAttributeMapList(NumberFirePageFixture.getNumberFireRemainingSeasonCentersProjectionsPageDocument());
        assertEquals("Did not find the correct number of centers in the remaining season projections", 126, playersAttributes.size());
    }

    @Test
    public void shouldBeAbleToFindOneHundredFortyNinePlayersInTheNumberFireDailyProjectionsPage(){
        NumberFireJsonPageScraper scraper = new NumberFireJsonPageScraper();
        List<Map<String, String>> playersAttributes = scraper.getProjectionAttributeMapList(NumberFirePageFixture.getNumberFireDailyProjectionsPageDocument());
        assertEquals("Did not find the correct number of players in the daily projections", 149, playersAttributes.size());
    }

    @Test
    public void shouldBeAbleToParseRandomForwardsRemainingSeasonProjectionsCorrectly(){
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
    public void shouldBeAbleToParseRandomForwardsDailyProjectionsCorrectly(){
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
}
