package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.models.EspnPositionEligibilityModel;
import com.btanabe2.fbdu.dp.data.providers.NbaPositionProvider;
import com.btanabe2.fbdu.dp.data.scrapers.EspnProjectionsPageScraper;
import com.btanabe2.fbdu.dp.fixtures.FileDocumentor;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

/**
 * Created by brian on 2/2/15.
 */
public class EspnProjectionsPageScraperTests {
    private static List<EspnPositionEligibilityModel> positionEligibilityModels;

    @BeforeClass
    public static void setup() {
        positionEligibilityModels = EspnProjectionsPageScraper.getEspnPlayerPositionEligibilities(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/espn-team-pages/espn-playerrater1.html"), NbaPositionProvider.getAllPositions());

        positionEligibilityModels.forEach(p -> System.out.println(p.toString()));
    }

    @Test
    public void shouldBeAbleToFindSeventyThreeEspnPositionEligibilityModelsOnProjectionsPageOne() {
        assertEquals("Did not find 73 players on projection page one", 76, positionEligibilityModels.size());
    }

    @Test
    public void shouldBeAbleToParseAnEspnPlayerIdForAllEspnPositionEligibilityModels() {
        assertFalse(String.format("Found [%s] positionEligibilityModels with an ESPN ID of 0", positionEligibilityModels.stream().filter(p -> p.getEspnPlayerId() == 0).collect(Collectors.toList()).size()), positionEligibilityModels.stream().anyMatch(p -> p.getEspnPlayerId() == 0));
    }

    @Test
    public void shouldBeAbleToParsePlayersWithOnePositionEligibilityModelsCorrectly() {
        List<EspnPositionEligibilityModel> positionEligibilityModelList = positionEligibilityModels.stream().filter(p -> p.getEspnPlayerId() == 3992).collect(Collectors.toList());
        assertEquals("James Harden should have one eligible positions", 1, positionEligibilityModelList.size());
        assertTrue("James Harden should be SG eligible", positionEligibilityModelList.stream().anyMatch(p -> p.getPositionId() == 2));
    }

    @Test
    public void shouldBeAbleToParsePlayersWithTwoPositionEligibilityModelsCorrectly() {
        List<EspnPositionEligibilityModel> positionEligibilityModelList = positionEligibilityModels.stream().filter(p -> p.getEspnPlayerId() == 1966).collect(Collectors.toList());
        assertEquals("LeBron James should have two eligible positions", 2, positionEligibilityModelList.size());
        assertTrue("LeBron James should be SF eligible", positionEligibilityModelList.stream().anyMatch(p -> p.getPositionId() == 3));
        assertTrue("LeBron James should be PF eligible", positionEligibilityModelList.stream().anyMatch(p -> p.getPositionId() == 4));
    }

    @Test
    public void shouldBeAbleToParsePlayersWithTextAfterTheirPositionListCorrectly() {
        List<EspnPositionEligibilityModel> positionEligibilityModelList = positionEligibilityModels.stream().filter(p -> p.getEspnPlayerId() == 996).collect(Collectors.toList());
        assertEquals("Paul Gasol should have two eligible positions", 2, positionEligibilityModelList.size());
        assertTrue("Pau Gasol should be PF eligible", positionEligibilityModelList.stream().anyMatch(p -> p.getPositionId() == 4));
        assertTrue("Pau Gasol should be C eligible", positionEligibilityModelList.stream().anyMatch(p -> p.getPositionId() == 5));
    }
}
