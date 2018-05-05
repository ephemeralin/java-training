package ru.job4j.musicplace.model.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.musicplace.model.entity.IEntity;
import ru.job4j.musicplace.model.entity.Role;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * The type Role dao test.
 */
public class RoleDAOTest {
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
        for (Role role : roles) {
            RoleDAO.getInstance().delete(role);
        }
    }

    /**
     * Create test.
     */
    @Test
    public void createTest() {
        assertNotEquals(0, roles.get(0).getId());
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Role foundUser = RoleDAO.getInstance().findById(roles.get(0).getId());
        assertThat(foundUser.getName(), is("testRole123"));
    }

    /**
     * Find by name test.
     */
    @Test
    public void findByNameTest() {
        Role foundUser = RoleDAO.getInstance().findByName(roles.get(0).getName());
        assertThat(foundUser.getName(), is("testRole123"));
    }

    /**
     * Find all test.
     */
    @Test
    public void findAllTest() {
        Role secondTestRole = TestDataFactory.createTestRole("testRole456");
        roles.add(secondTestRole);
        List<? extends IEntity> all = RoleDAO.getInstance().findAll();
        assertThat(all.size(), greaterThanOrEqualTo(2));
    }

    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        Role testRole = roles.get(0);
        testRole.setName("testRole456");
        RoleDAO.getInstance().update(testRole);
        Role foundRole = RoleDAO.getInstance().findById(testRole.getId());
        assertThat(foundRole.getName(), is("testRole456"));
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        boolean isDeleted = RoleDAO.getInstance().delete(roles.get(0));
        assertTrue(isDeleted);
        assertNull(RoleDAO.getInstance().findByName("testRole123"));
    }
}