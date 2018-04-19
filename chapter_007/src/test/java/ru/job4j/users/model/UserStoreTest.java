package ru.job4j.users.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * The type User store test.
 */
public class UserStoreTest {

    /**
     * Is identified.
     */
    @Test
    public void isIdentified() {
        assertTrue(UserStore.getInstance().isIdentified("root", "root"));
    }
}