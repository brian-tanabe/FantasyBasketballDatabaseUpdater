package com.btanabe2.fbdu.dp.suites;

import com.btanabe2.fbdu.dp.tests.unit.web.SecureWebRequestTests;
import com.btanabe2.fbdu.dp.tests.unit.web.WebRequestTests;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * Created by Brian on 2/10/15.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        SecureWebRequestTests.class,
        WebRequestTests.class
})
public class WebTestSuite {
}
