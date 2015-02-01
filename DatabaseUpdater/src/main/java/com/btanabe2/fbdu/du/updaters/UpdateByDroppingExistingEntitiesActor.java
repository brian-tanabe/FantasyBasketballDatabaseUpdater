package com.btanabe2.fbdu.du.updaters;

import com.btanabe2.fbdu.du.application.MySessionManager;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 * Created by brian on 1/30/15.
 */
public class UpdateByDroppingExistingEntitiesActor <T> {
    private static Transaction currentTransaction;

    public static <T> void doUpdate(Class<T> clazz, List<T> entities) {
        removeAllCurrentEntitiesFromDatabase(clazz);
        addNewEntitiesToDatabase(entities);
    }

    private static <T> void removeAllCurrentEntitiesFromDatabase(Class<T> clazz) {
        Session session = MySessionManager.getInstance().getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        session.createCriteria(clazz).list().forEach(session::delete);
        transaction.commit();
    }

    private static <T> void addNewEntitiesToDatabase(List<T> entities) {
        Session session = MySessionManager.getInstance().getSessionFactory().getCurrentSession();
        Transaction transaction = session.beginTransaction();
        entities.forEach(session::saveOrUpdate);
        transaction.commit();
    }
}
