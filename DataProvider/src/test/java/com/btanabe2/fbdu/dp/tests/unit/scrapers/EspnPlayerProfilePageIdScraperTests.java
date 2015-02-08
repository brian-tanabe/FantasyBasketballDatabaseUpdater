package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnPlayerProfilePageIdScraper;
import com.btanabe2.fbdu.dp.helpers.FileDocumentor;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnPlayerProfilePageIdScraperTests {
    private static Integer pauGasolsEspnId = 996;
    private static Map<Integer, Integer> playerIdMappedToHisFantasyId;

    @BeforeClass
    public static void setup() {
        playerIdMappedToHisFantasyId = EspnPlayerProfilePageIdScraper.getPlayerFantasyIdMappedToHisEspnId(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/espn-player-pages/pau-gasol-player-page.html"), pauGasolsEspnId);
    }

    @Test
    public void shouldBeAbleToMapPauGasolsFantasyIdToHisEspnId() {
        assertEquals("Did not find Pau Gasol's ESPN ID properly", pauGasolsEspnId, playerIdMappedToHisFantasyId.get(162));
    }
}
