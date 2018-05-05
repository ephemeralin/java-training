package ru.job4j.musicplace.model.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.musicplace.model.entity.IEntity;
import ru.job4j.musicplace.model.entity.Role;
import ru.job4j.musicplace.model.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * The type User dao test.
 */
public class UserDAOTest {
    /**
     * Test users list.
     */
    private List<User> users = new ArrayList<>();
    /**
     * Test roles list.
     */
    private List<Role> roles = new ArrayList<>();

    /**
     * Before class test.
     */
    @BeforeClass
    public static void beforeClassTest() {
        MainRepository.getInstance();
    }

    /**
     * Before test.
     */
    @Before
    public void beforeTest() {
        deleteAllTestData();
        Role role = TestDataFactory.createTestRole("testRole123");
        roles.add(role);
        User user = TestDataFactory.createTestUser("testUser123", "testPassword123", role);
        users.add(user);
    }

    /**
     * After test.
     */
    @After
    public void afterTest() {
        deleteAllTestData();
    }

    /**
     * Delete all test data from DB.
     */
    private void deleteAllTestData() {
        for (User user : users) {
            UserDAO.getInstance().delete(user);
        }
        for (Role role : roles) {
            RoleDAO.getInstance().delete(role);
        }
    }

    /**
     * Create test.
     */
    @Test
    public void createTest() {
        assertNotEquals(0, users.get(0).getId());
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        User foundUser = UserDAO.getInstance().findById(users.get(0).getId());
        assertThat(foundUser.getName(), is("testUser123"));
    }

    /**
     * Find by name test.
     */
    @Test
    public void findByNameTest() {
        User foundUser = UserDAO.getInstance().findByName("testUser123");
        assertThat(foundUser.getName(), is("testUser123"));

    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        boolean isDeleted = UserDAO.getInstance().delete(users.get(0));
        assertTrue(isDeleted);
        assertNull(UserDAO.getInstance().findByName("testUser123"));
    }

    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        User testUser = users.get(0);
        testUser.setName("testUser456");
        UserDAO.getInstance().update(testUser);
        User foundUser = UserDAO.getInstance().findById(testUser.getId());
        assertThat(foundUser.getName(), is("testUser456"));
    }

    /**
     * Find all test.
     */
    @Test
    public void findAllTest() {
        Role secondTestRole = TestDataFactory.createTestRole("testRole456");
        User secondTestUser = TestDataFactory.createTestUser("testUser456", "testPassword123", secondTestRole);
        roles.add(secondTestRole);
        users.add(secondTestUser);
        List<? extends IEntity> all = UserDAO.getInstance().findAll();
        assertThat(all.size(), greaterThanOrEqualTo(2));
    }
}