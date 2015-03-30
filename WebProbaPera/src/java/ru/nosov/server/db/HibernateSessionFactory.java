/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ru.nosov.server.db;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 *
 * @author nosov
 */
public class HibernateSessionFactory {

    // Variables declaration
    private static final Logger logger = Logger.getLogger(HibernateSessionFactory.class);
    private static final SessionFactory sessionFactory;
    // End of variables declaration

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure("hibernate.cfg.xml");
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(
                    configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistry);
        } catch (Throwable ex) {
            logger.error("Initial HibernateSessionFactory creation failed. ", ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
