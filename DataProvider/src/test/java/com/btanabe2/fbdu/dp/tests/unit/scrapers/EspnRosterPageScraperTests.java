package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dp.data.scrapers.EspnRosterPageScraper;
import org.junit.BeforeClass;

import java.io.IOException;

import static com.btanabe2.fbdu.dp.mocks.MockWebRequest.getEspnRosterPageScraperMockSecureWebRequest;
import static junit.framework.TestCase.fail;

/**
 * Created by Brian on 2/11/15.
 */
public class EspnRosterPageScraperTests {

    @BeforeClass
    public static void setup() {
        try {
            EspnRosterPageScraper scraper = new EspnRosterPageScraper(getEspnRosterPageScraperMockSecureWebRequest());
            scraper.scrapeEspnRosterPage();
        } catch (IOException e) {
            e.printStackTrace();
            fail("Failed to scrape ESPN's roster page");
        }
    }
}
