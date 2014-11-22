package com.btanabe2.fbdu.dp.tests.unit.scrapers;

import com.btanabe2.fbdu.dm.models.PlayerBiographyEntity;
import com.btanabe2.fbdu.dp.fixtures.FileDocumentor;
import com.btanabe2.fbdu.dp.fixtures.NbaTeamEntityFixture;
import com.btanabe2.fbdu.dp.stats.scrapers.EspnAndNumberFireIdPageScraper;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.TestCase.*;

/**
 * Created by BTanabe on 11/18/2014.
 */
public class EspnAndNumberFireIdPageScraperTests {
    private static List<PlayerBiographyEntity> guardsBiographies;
    private static List<PlayerBiographyEntity> forwardsBiographies;
    private static List<PlayerBiographyEntity> centersBiographies;

    @BeforeClass
    public static void setup(){
        try {
            EspnAndNumberFireIdPageScraper scraper = new EspnAndNumberFireIdPageScraper();

            guardsBiographies = scraper.scrapePlayerBiographiesFromPage(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-guards.html"), NbaTeamEntityFixture.getMockNbaTeams());
            forwardsBiographies = scraper.scrapePlayerBiographiesFromPage(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-forwards.html"), NbaTeamEntityFixture.getMockNbaTeams());
            centersBiographies = scraper.scrapePlayerBiographiesFromPage(FileDocumentor.getDocumentFromFileHtml("./DataProvider/src/test/resources/webpages/number-fire-pages/number-fire-remaining-season-projections-centers.html"), NbaTeamEntityFixture.getMockNbaTeams());
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
        assertEquals("Player's NBA team was not parsed correctly", 7, player.getNbateamid());
    }

    @Test
    public void shouldParseKyleKorverCorrectly(){
        PlayerBiographyEntity player = forwardsBiographies.stream().filter(p -> p.getName().equals("Kyle Korver")).limit(1).collect(Collectors.toList()).get(0);

        assertNotNull("Did not find Kyle Korver", player);
        assertEquals("Did not find Kyle Korver", "Kyle Korver", player.getName());
        assertEquals("Player's ESPN ID was not parsed correctly", 2011, player.getEspnid());
        assertEquals("Player's NumberFire ID was not parsed correctly", 328, player.getNumberfireid());
        assertEquals("Player's NBA team was not parsed correctly", 1, player.getNbateamid());
    }

    @Test
    public void shouldParseDeMarcusCousinsCorrectly(){
        PlayerBiographyEntity player = centersBiographies.stream().filter(p -> p.getName().equals("DeMarcus Cousins")).limit(1).collect(Collectors.toList()).get(0);

        assertNotNull("Did not find DeMarcus Cousins", player);
        assertEquals("Did not find DeMarcus Cousins", "DeMarcus Cousins", player.getName());
        assertEquals("Player's ESPN ID was not parsed correctly", 4258, player.getEspnid());
        assertEquals("Player's NumberFire ID was not parsed correctly", 136, player.getNumberfireid());
        assertEquals("Player's NBA team was not parsed correctly", 23, player.getNbateamid());
    }

    @Test
    public void shouldParseJoseJuanBareaCorrectlySinceHeHasNoBirthdayOrExperienceDataOnHisNumberFireProfile(){
        PlayerBiographyEntity player = guardsBiographies.stream().filter(p -> p.getName().equals("Juan Jose Barea")).limit(1).collect(Collectors.toList()).get(0);

        assertNotNull("Unable to find Juan Jose Barea using his name as a key", player);
        assertEquals("Juan Jose Barea", player.getName());
        assertEquals("Barea's NumberFireID was not parsed correctly", 470, player.getNumberfireid());
        assertEquals("Barea's ESPN ID was not parsed corretly", 3055, player.getEspnid());
        assertEquals("Barea's NBA team ID was not parsed correctly", 1610612742, player.getNbateamid());
    }

    @Test
    public void shouldBeAbleToParseArronAfflalosInfoProperly(){
        PlayerBiographyEntity player = guardsBiographies.stream().filter(p -> p.getName().equals("Arron Afflalo")).limit(1).collect(Collectors.toList()).get(0);

        assertEquals(3187, player.getEspnid());
        assertEquals(123, player.getNumberfireid());
    }
}
