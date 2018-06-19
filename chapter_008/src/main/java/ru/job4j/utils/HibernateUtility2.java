package ru.job4j.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

/**
 * The enum Hibernate utility 2.
 */
public enum HibernateUtility2 {
    /**
     * The Instance.
     */
    INSTANCE("hibernate.cfg.xml");

    /**
     * Session factory.
     */
    private final SessionFactory sessionFactory;

    /**
     * Hibernate utility default constructor.
     * @param fileName name of config file
     */
    HibernateUtility2(String fileName) {
            Logger log = LogManager.getLogger(HibernateUtility.class);
            try {
                StandardServiceRegistry standardRegistry = new StandardServiceRegistryBuilder() .configure(fileName).build();
                Metadata metadata = new MetadataSources(standardRegistry).getMetadataBuilder().build();
                sessionFactory = metadata.getSessionFactoryBuilder().build();
                log.info("Initial SessionFactory created");
            } catch (Throwable ex) {
                log.error("Initial SessionFactory creation failed. " + ex);
                throw new ExceptionInInitializerError(ex);
            }
        }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public HibernateUtility2 getInstance() {
            return INSTANCE;
        }

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
