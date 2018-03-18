package ru.job4j.utils;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;

/**
 * The type All echo context.
 */
public class AllServletsContext implements ServletContextListener {
    /**
     * The Context.
     */
    private ServletContext context;

    /**
     * The Log.
     */
    private Logger log;

    /**
     * Context initialized.
     *
     * @param contextEvent the context event
     */
    public final void contextInitialized(ServletContextEvent contextEvent) {
        log = LogManager.getLogger(this.getClass());
        log.info("Servlets Context created.");
        context = contextEvent.getServletContext();
        context.setAttribute("TEST", "TEST_VALUE");
    }

    /**
     * Context destroyed.
     *
     * @param contextEvent the context event
     */
    public final void contextDestroyed(ServletContextEvent contextEvent) {
        context = contextEvent.getServletContext();
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            if (driver.getClass().getClassLoader() == cl) {
                try {
                    log.info("Deregistering JDBC driver {}", driver);
                    DriverManager.deregisterDriver(driver);
                } catch (SQLException ex) {
                    log.error("Error deregistering JDBC driver {}", driver, ex);
                }
            } else {
                // driver was not registered by the webapp's ClassLoader and may be in use elsewhere
                log.trace("Not deregistering JDBC driver {} as it does not belong to this webapp's ClassLoader", driver);
            }
        }
        log.info("Servlets Context destroyed.");
    }
}
