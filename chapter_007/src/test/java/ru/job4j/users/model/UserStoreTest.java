package ru.job4j.users.model;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class UserStoreTest {

    @Test
    public void isIdentified() {
        assertTrue(UserStore.getInstance().isIdentified("root", "root"));
    }
}