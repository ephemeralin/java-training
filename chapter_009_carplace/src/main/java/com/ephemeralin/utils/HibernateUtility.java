package com.ephemeralin.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * The type Hibernate utility.
 */
public class HibernateUtility {
    /**
     * The constant factory.
     */
    private static SessionFactory factory;

    /**
     * Private constructor.
     */
    private HibernateUtility() {
    }

    /**
     * Gets session factory.
     *
     * @param cfgFileName the cfg file name
     * @return the session factory
     */
    public static synchronized SessionFactory getSessionFactory(String cfgFileName) {
        if (factory == null) {
            Logger log = LogManager.getLogger(HibernateUtility.class);
            try {
                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder() .configure(cfgFileName).build();
                Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
                factory = metadata.getSessionFactoryBuilder().build();
                log.info("Initial SessionFactory created");
            } catch (Throwable ex) {
                log.error("Initial SessionFactory creation failed. " + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return factory;
    }
}