package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnPlayerProfilePageIdScraper;
import com.btanabe2.fbdu.dp.helpers.FileDocumentor;
import org.junit.Test;

import java.util.Map;

import static org.junit.Assert.assertEquals;

/**
 * Created by Brian on 2/7/15.
 */
public class EspnPlayerProfilePageIdScraperTests {

    @Test
    public void shouldBeAbleToMapPauGasolsFantasyIdToHisEspnId() {
        Integer pauGasolsEspnId = 996;
        assertEquals("Did not find Pau Gasol's ESPN ID properly", pauGasolsEspnId, getPlayerFantasyIdFromProfilePage("./DataProvider/src/test/resources/webpages/espn-player-pages/espn-player-profile-page-996_pau-gasol.html", pauGasolsEspnId).get(162));
    }

    @Test
    public void shouldBeAbleToMapTimFraziersIdsCorrectlyWhichHasUndraftedListedAsHisAverageDraftPosition() {
        Integer timFraziersEspnId = 2488945;
        assertEquals("Did not find Tim Frazier's ESPN ID properly", timFraziersEspnId, getPlayerFantasyIdFromProfilePage("./DataProvider/src/test/resources/webpages/espn-player-pages/espn-player-profile-page-2488945_tim-frazier.html", 2488945).get(1329));
    }

    @Test
    public void shouldBeAbleToMapKostasPapanikolaousIdCorrectly() {
        Integer kostasPapanikolaousEspnId = 4195;
        assertEquals("Did not find Kostas Papanikolaous' ESPN ID properly", kostasPapanikolaousEspnId, getPlayerFantasyIdFromProfilePage("./DataProvider/src/test/resources/webpages/espn-player-pages/espn-player-profile-page-4195_kostas-papanikolaou.html", 4195).get(959));
    }

    private Map<Integer, Integer> getPlayerFantasyIdFromProfilePage(String pathToProfilePage, int playerEspnId) {
        return EspnPlayerProfilePageIdScraper.getPlayerFantasyIdMappedToHisEspnId(FileDocumentor.getDocumentFromFileHtml(pathToProfilePage), playerEspnId);
    }
}
