package ru.job4j.utils;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Properties storage test.
 */
public class PropertiesStorageTest {

    /**
     * Gets property test.
     */
    @Test
    public void getPropertyTest() {
        final PropertiesStorage propertiesStorage = new PropertiesStorage("/properties.properties");
        final String property = propertiesStorage.getProperty("db.password");
        assertThat(property, is("password"));
    }
}