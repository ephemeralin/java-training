package ru.job4j.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Properties;

/**
 * The type Properties storage.
 */
public class PropertiesStorage {

    /**
     * File name.
     */
    private String propertiesFileName;
    /**
     * Logger.
     */
    private final Logger log;
    /**
     * Properties.
     */
    private Properties properties;


    /**
     * Instantiates a new Properties storage.
     *
     * @param propertiesFileName properties file name
     */
    public PropertiesStorage(String propertiesFileName) {
        this.propertiesFileName = propertiesFileName;
        this.log = LogManager.getLogger(this.getClass());
        updatePropertiesFromFile(propertiesFileName);
    }

    /**
     * Update properties from file.
     *
     * @param path the path
     */
    public void updatePropertiesFromFile(String path) {
        try {
            InputStream is = getClass().getResourceAsStream(propertiesFileName);
            this.properties = new Properties();
            this.properties.load(is);
        } catch (Exception e) {
            log.error("Properties file was not found!", e);
        }
    }

    /**
     * Sets property.
     *
     * @param propertyName the property name
     * @param value        the value
     */
    public void setProperty(String propertyName, String value) {
        properties.setProperty(propertyName, value);
        FileOutputStream out = null;
        try {
            out = new FileOutputStream(createPropertiesFile(propertiesFileName));
            properties.store(out, null);
            log.info(String.format("New property set: %s = %s", propertyName, value));
        } catch (FileNotFoundException e) {
            log.error("Properties file was not found!", e);
        } catch (IOException e) {
            log.error("Properties file saving error!", e);
        } catch (URISyntaxException e) {
            log.error("Properties file saving error!", e);
        } finally {
            if (out != null) {
                try {
                    out.close();
                } catch (IOException e1) {
                    log.error("Properties out stream closing error!", e1);
                }
            }
        }
    }

    /**
     * Create properties file.
     * @param relativeFilePath the Path
     * @return new File
     * @throws URISyntaxException the exception.
     */
    private File createPropertiesFile(String relativeFilePath) throws URISyntaxException {
        return new File(new File(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI()), relativeFilePath);
    }

    /**
     * Gets property.
     *
     * @param s the string property
     * @return the property
     */
    public String getProperty(String s) {
        return properties.getProperty(s);
    }
}
