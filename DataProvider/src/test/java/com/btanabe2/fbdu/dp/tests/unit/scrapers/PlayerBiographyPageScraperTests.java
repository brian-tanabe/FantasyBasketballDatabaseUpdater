package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.fixtures.FileDocumentor;
import com.btanabe2.fbdu.dp.stats.scrapers.PlayerBiographyPageScraper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.*;

/**
 * Created by BTanabe on 11/18/2014.
 */
public class PlayerBiographyPageScraperTests {
    private static List<PlayerBiographyEntity> guardsBiographies;
    private static List<PlayerBiographyEntity> forwardsBiographies;
    private static List<PlayerBiographyEntity> centersBiographies;

    @BeforeClass
    public static void setup(){
        try {
            PlayerBiographyPageScraper scraper = new PlayerBiographyPageScraper();

            guardsBiographies = scraper.scrapePlayerBiographiesFromPage(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-guards.html"));
            forwardsBiographies = scraper.scrapePlayerBiographiesFromPage(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-forwards.html"));
            centersBiographies = scraper.scrapePlayerBiographiesFromPage(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-centers.html"));
        } catch (Exception ex){
            ex.printStackTrace();
            fail("Failed to parse NumberFire input files properly");
        }
    }

    @Test
    public void shouldFindTwoHundredNineGuards(){
        assertEquals(209, guardsBiographies.size());
    }

    @Test
    public void shouldFindTwoHundredFiftyForwards(){
        assertEquals(250, forwardsBiographies.size());
    }

    @Test
    public void shouldFindOneTwentySixCenters(){
        assertEquals(126, centersBiographies.size());
    }

    @Test
    public void shouldParseTyLawsonCorrectly(){
        PlayerBiographyEntity player = guardsBiographies.stream().filter(p -> p.getName().equals("Ty Lawson")).limit(1).collect(Collectors.toList()).get(0);

        assertNotNull("Did not find Ty Lawson", player);
        assertEquals("Did not find Ty Lawson", "Ty Lawson", player.getName());
        assertEquals("Player's ESPN ID was not parsed correctly", 4000, player.getEspnid());
        assertEquals("Player's NumberFire ID was not parsed correctly", 132, player.getNumberfireid());
        assertEquals("Player's experience was not parsed correctly", 2, player.getExperience());
        assertEquals("Player's NBA team was not parsed correctly", 7, player.getNbateamid());
        assertEquals("Player's birthday was not parsed correctly", "1987-11-03", player.getBirthday().toString());
    }

    @Test
    public void shouldParseKyleKorverCorrectly(){
        PlayerBiographyEntity player = forwardsBiographies.stream().filter(p -> p.getName().equals("Kyle Korver")).limit(1).collect(Collectors.toList()).get(0);

        assertNotNull("Did not find Kyle Korver", player);
        assertEquals("Did not find Kyle Korver", "Kyle Korver", player.getName());
        assertEquals("Player's ESPN ID was not parsed correctly", 2011, player.getEspnid());
        assertEquals("Player's NumberFire ID was not parsed correctly", 328, player.getNumberfireid());
        assertEquals("Player's experience was not parsed correctly", 8, player.getExperience());
        assertEquals("Player's NBA team was not parsed correctly", 1, player.getNbateamid());
        assertEquals("Player's birthday was not parsed correctly", "1981-03-17", player.getBirthday().toString());
    }

    @Test
    public void shouldParseDeMarcusCousinsCorrectly(){
        PlayerBiographyEntity player = centersBiographies.stream().filter(p -> p.getName().equals("DeMarcus Cousins")).limit(1).collect(Collectors.toList()).get(0);

        assertNotNull("Did not find DeMarcus Cousins", player);
        assertEquals("Did not find DeMarcus Cousins", "DeMarcus Cousins", player.getName());
        assertEquals("Player's ESPN ID was not parsed correctly", 4258, player.getEspnid());
        assertEquals("Player's NumberFire ID was not parsed correctly", 136, player.getNumberfireid());
        assertEquals("Player's NBA team was not parsed correctly", 23, player.getNbateamid());
        assertEquals("Player's birthday was not parsed correctly", "1990-08-13", player.getBirthday().toString());
        assertEquals("Player's experience was not parsed correctly", 3, player.getExperience());        // NF never updated their player bio info.  Will switch to using SportsVu for everything but projections.
    }

    @Test
    public void shouldParseJoseJuanBerreaCorrectlySinceHeHasNoBirthdayOrExperienceDataOnHisNumberFireProfile(){
        fail("Unimplemented test!");
    }
}
