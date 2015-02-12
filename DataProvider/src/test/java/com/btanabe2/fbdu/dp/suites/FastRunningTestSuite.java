package com.btanabe2.fbdu.dp.suites;

import com.btanabe2.fbdu.dp.tests.integration.EspnFantasyIdToStandardIdProviderTests;
import com.btanabe2.fbdu.dp.tests.integration.NbaTeamProviderTests;
import com.btanabe2.fbdu.dp.tests.integration.PlayerBiographyProviderTests;
import com.btanabe2.fbdu.dp.tests.integration.PositionEligibilityProviderTests;
import com.btanabe2.fbdu.dp.tests.unit.providers.CurrentNbaSeasonStartYearProviderTests;
import com.btanabe2.fbdu.dp.tests.unit.providers.NbaPositionProviderTests;
import com.btanabe2.fbdu.dp.tests.unit.scrapers.*;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Brian on 2/10/15.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        EspnFantasyIdToStandardIdProviderTests.class,
        NbaTeamProviderTests.class,
        PlayerBiographyProviderTests.class,
        PositionEligibilityProviderTests.class,
        CurrentNbaSeasonStartYearProviderTests.class,
        NbaPositionProviderTests.class,
        EspnAndNumberFireIdPageScraperTests.class,
        EspnLeagueIdAndTeamIdScraperTests.class,
        EspnPlayerProfileLinkScraperTests.class,
        EspnPlayerProfilePageIdScraperTests.class,
        EspnProjectionsPageScraperTests.class,
        EspnTeamRosterLinkScraperTests.class,
        NbaTeamJsonParserTests.class,
        NbaTeamSportsVuScraperTests.class,
        NumberFireDailyProjectionsScraperTests.class,
        NumberFireJsonPageScraperTests.class,
        NumberFireRankingsScraperTests.class,
        NumberFireRemainingSeasonProjectionsPageScraperTests.class,
        PlayerProfileSportsVuScraperTests.class,
        EspnRosterPageScraperTests.class
})
public class FastRunningTestSuite {
}
