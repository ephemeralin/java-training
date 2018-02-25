package ru.job4j.siteparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Properties;

/**
 * The type Properties storage.
 */
public class PropertiesStorage {
    /**
     * Logger.
     */
    private final Logger logger;
    /**
     * Properties path.
     */
    private String path;
    /**
     * Properties.
     */
    private Properties properties;


    /**
     * Instantiates a new Properties storage.
     *
     * @param resourcesPath the resources path
     */
    public PropertiesStorage(String resourcesPath) {
        this.logger = LogManager.getLogger(PropertiesStorage.class);
        this.path = resourcesPath + "siteparser.properties";
        updatePropertiesFromFile();
    }

    /**
     * Update properties from file.
     */
    public void updatePropertiesFromFile() {
        try {
            InputStream is = new FileInputStream(new File(path));
            this.properties = new Properties();
            this.properties.load(is);
        } catch (Exception e) {
            logger.error("Properties file was not found!", e);
        }
    }

    /**
     * Sets property.
     *
     * @param propertyName the property name
     * @param value        the value
     */
    public void setProperty(String propertyName, String value) {
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(path);
        } catch (FileNotFoundException e) {
            logger.error("Properties file was not found!", e);
        }
        properties.setProperty(propertyName, value);
        try {
            properties.store(out, null);
            out.close();
        } catch (IOException e) {
            logger.error("Properties file saving error!", e);
        }
    }

    /**
     * Gets property.
     *
     * @param s the s
     * @return the property
     */
    public String getProperty(String s) {
        return properties.getProperty(s);
    }

    /**
     * Gets property period in millis.
     *
     * @return the property period in millis
     */
    public Long getPropertyPeriodInMillis() {
        return Long.decode(getProperty("app.periodInMinutes")) * 1000 * 60;
    }

    /**
     * Gets property is first run.
     *
     * @return the property is first run
     */
    public boolean getPropertyIsFirstRun() {
        return Boolean.parseBoolean(getProperty("app.isFirstRun"));
    }
}
