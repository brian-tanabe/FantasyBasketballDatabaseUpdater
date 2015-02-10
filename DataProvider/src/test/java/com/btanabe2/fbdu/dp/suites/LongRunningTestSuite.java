package com.btanabe2.fbdu.dp.suites;

import com.btanabe2.fbdu.dp.tests.time.TimedEspnFantasyIdToStandardIdProviderTests;
import com.btanabe2.fbdu.dp.tests.time.TimedEspnTeamRosterLinkScraperTests;
import com.btanabe2.fbdu.dp.tests.time.TimedPlayerProfileSportsVuScraperTests;
import com.btanabe2.fbdu.dp.tests.time.TimedPositionEligibilityProviderTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * Created by Brian on 2/10/15.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        TimedEspnFantasyIdToStandardIdProviderTests.class,
        TimedEspnTeamRosterLinkScraperTests.class,
        TimedPlayerProfileSportsVuScraperTests.class,
        TimedPositionEligibilityProviderTests.class
})
public class LongRunningTestSuite {
}
