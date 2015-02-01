package com.btanabe2.fbdu.du.application;

import com.btanabe2.fbdu.dm.configuration.HibernateConfiguration;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * Created by brian on 1/30/15.
 */
public class MySessionManager {
    private static MySessionManager instance;
    public static MySessionManager getInstance() {
        return instance == null ? new MySessionManager() : instance;
    }

    private SessionFactory sessionFactory;

    private MySessionManager() {
        buildSessionFactory();
    }

    private void buildSessionFactory() {
        Configuration configuration = new Configuration();
        configuration.configure(HibernateConfiguration.getHibernateConfigurationFile());

        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

        sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        sessionFactory.openSession();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
