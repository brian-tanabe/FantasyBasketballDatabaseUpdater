package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dm.models.NfRemainingSeasonProjectionsEntity;
import com.btanabe2.fbdu.dp.data.scrapers.NumberFireRemainingSeasonProjectionsPageScraper;
import com.btanabe2.fbdu.dp.helpers.FileDocumentor;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Created by BTanabe on 11/10/2014.
 */
public class NumberFireRemainingSeasonProjectionsPageScraperTests {
    private static List<NfRemainingSeasonProjectionsEntity> guardsProjections;
    private static List<NfRemainingSeasonProjectionsEntity> forwardsProjections;
    private static List<NfRemainingSeasonProjectionsEntity> centersProjections;

    @BeforeClass
    public static void setup() {
        NumberFireRemainingSeasonProjectionsPageScraper scraper = new NumberFireRemainingSeasonProjectionsPageScraper();

        guardsProjections = scraper.scrapeRemainingSeasonProjectionsFromPage(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-guards.html"));
        forwardsProjections = scraper.scrapeRemainingSeasonProjectionsFromPage(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-forwards.html"));
        centersProjections = scraper.scrapeRemainingSeasonProjectionsFromPage(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-centers.html"));
    }

    @Test
    public void shouldFindTwoHundredNineGuards() {
        assertEquals(209, guardsProjections.size());
    }

    @Test
    public void shouldFindTwoHundredFiftyForwards() {
        assertEquals(250, forwardsProjections.size());
    }

    @Test
    public void shouldFindOneTwentySixCenters() {
        assertEquals(126, centersProjections.size());
    }
}
