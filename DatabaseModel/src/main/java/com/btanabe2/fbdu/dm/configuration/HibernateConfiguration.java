package com.btanabe2.fbdu.dm.configuration;

import java.io.File;

/**
 * Created by brian on 1/30/15.
 */
public class HibernateConfiguration {

    public static File getHibernateConfigurationFile() {
        return new File("./DatabaseModel/src/hibernate.cfg.xml");
    }

    public static File getCurrentDirectory() { return new File("."); }
}
