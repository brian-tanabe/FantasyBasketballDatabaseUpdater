package com.btanabe2.fbdu.dp.tests.unit.providers;

import com.btanabe2.fbdu.dm.models.PositionsEntity;
import com.btanabe2.fbdu.dp.leagues.providers.NbaPositionProvider;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertNotNull;
import static junit.framework.TestCase.assertEquals;

/**
 * Created by brian on 1/25/15.
 */
public class NbaPositionProviderTests {
    private static List<PositionsEntity> positionList;

    @BeforeClass
    public static void getAllNbaPositions(){
        positionList = NbaPositionProvider.getAllPositions();
    }

    @Test
    public void shouldBeAbleToCreateFivePositions() {
        assertEquals("Did not create five positions", 5, positionList.size());
    }

    @Test
    public void shouldBeAbleToCreatePointGuardsWithTheProperAbbreviation() {
        String positionAbbreviation = "PG";
        PositionsEntity position = positionList.stream().filter(p -> p.getAbbreviation().equals(positionAbbreviation)).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with an abbreviation of " + positionAbbreviation, position);
    }

    @Test
    public void shouldBeAbleToCreatePointGuardsWithTheProperLongName() {
        String longName = "Point Guard";
        PositionsEntity position = positionList.stream().filter(p -> p.getFullname().equals(longName)).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with a full name of " + longName, position);
    }

    @Test
    public void shouldBeAbleToCreatePointGuardsWithTheProperId() {
        int id = 1;
        PositionsEntity position = positionList.stream().filter(p -> p.getId() == id).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with an ID of " + id, position);
    }

    @Test
    public void shouldBeAbleToCreateShootingGuardsWithTheProperAbbreviation() {
        String positionAbbreviation = "SG";
        PositionsEntity position = positionList.stream().filter(p -> p.getAbbreviation().equals(positionAbbreviation)).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with an abbreviation of " + positionAbbreviation, position);
    }

    @Test
    public void shouldBeAbleToCreateShootingGuardsWithTheProperLongName() {
        String longName = "Shooting Guard";
        PositionsEntity position = positionList.stream().filter(p -> p.getFullname().equals(longName)).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with a full name of " + longName, position);
    }

    @Test
    public void shouldBeAbleToCreateShootingGuardsWithTheProperId() {
        int id = 2;
        PositionsEntity position = positionList.stream().filter(p -> p.getId() == id).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with an ID of " + id, position);
    }

    @Test
         public void shouldBeAbleToCreateSmallForwardsWithTheProperAbbreviation() {
        String positionAbbreviation = "SF";
        PositionsEntity position = positionList.stream().filter(p -> p.getAbbreviation().equals(positionAbbreviation)).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with an abbreviation of " + positionAbbreviation, position);
    }

    @Test
    public void shouldBeAbleToCreateSmallForwardsWithTheProperLongName() {
        String longName = "Small Forward";
        PositionsEntity position = positionList.stream().filter(p -> p.getFullname().equals(longName)).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with a full name of " + longName, position);
    }

    @Test
    public void shouldBeAbleToCreateSmallForwardsWithTheProperId() {
        int id = 3;
        PositionsEntity position = positionList.stream().filter(p -> p.getId() == id).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with an ID of " + id, position);
    }

    @Test
    public void shouldBeAbleToCreatePowerForwardsWithTheProperAbbreviation() {
        String positionAbbreviation = "PF";
        PositionsEntity position = positionList.stream().filter(p -> p.getAbbreviation().equals(positionAbbreviation)).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with an abbreviation of " + positionAbbreviation, position);
    }

    @Test
    public void shouldBeAbleToCreatePowerForwardsWithTheProperLongName() {
        String longName = "Power Forward";
        PositionsEntity position = positionList.stream().filter(p -> p.getFullname().equals(longName)).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with a full name of " + longName, position);
    }

    @Test
    public void shouldBeAbleToCreatePowerForwardsWithTheProperId() {
        int id = 4;
        PositionsEntity position = positionList.stream().filter(p -> p.getId() == id).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with an ID of " + id, position);
    }

    @Test
    public void shouldBeAbleToCreateCentersWithTheProperAbbreviation() {
        String positionAbbreviation = "C";
        PositionsEntity position = positionList.stream().filter(p -> p.getAbbreviation().equals(positionAbbreviation)).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with an abbreviation of " + positionAbbreviation, position);
    }

    @Test
    public void shouldBeAbleToCreateCentersWithTheProperLongName() {
        String longName = "Center";
        PositionsEntity position = positionList.stream().filter(p -> p.getFullname().equals(longName)).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with a full name of " + longName, position);
    }

    @Test
    public void shouldBeAbleToCreateCentersWithTheProperId() {
        int id = 5;
        PositionsEntity position = positionList.stream().filter(p -> p.getId() == id).limit(1).collect(Collectors.toList()).get(0);
        assertNotNull("Did not find a PositionEntity with an ID of " + id, position);
    }
}
