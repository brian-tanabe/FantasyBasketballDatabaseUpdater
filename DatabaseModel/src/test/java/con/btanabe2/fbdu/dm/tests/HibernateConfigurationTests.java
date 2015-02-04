package con.btanabe2.fbdu.dm.tests;

import com.btanabe2.fbdu.dm.configuration.HibernateConfiguration;
import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.fail;

/**
 * Created by brian on 1/30/15.
 */
public class HibernateConfigurationTests {

    @Test
    public void shouldBeAbleToFindTheHibernateConfigurationXmlFile() {
        try {
            assertNotNull(HibernateConfiguration.getHibernateConfigurationFile());
        } catch (Exception ex) {
            fail("Failed to find or open the Hibernate configuration XML file");
        }
    }
}
