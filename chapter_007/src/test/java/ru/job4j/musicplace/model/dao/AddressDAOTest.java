package ru.job4j.musicplace.model.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.musicplace.model.entity.Address;
import ru.job4j.musicplace.model.entity.IEntity;
import ru.job4j.musicplace.model.entity.Role;
import ru.job4j.musicplace.model.entity.User;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertTrue;

/**
 * The type Address dao test.
 */
public class AddressDAOTest {
    /**
     * Test addresses list.
     */
    private List<Address> addresses = new ArrayList<>();
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
        Address address = TestDataFactory.createTestAddress("testAddress123", user);
        addresses.add(address);
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
        for (Address address : addresses) {
            AddressDAO.getInstance().delete(address);
        }
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
        assertThat(addresses.get(0).getId(), not(0));
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Address foundAddress = AddressDAO.getInstance().findById(addresses.get(0).getId());
        assertThat(foundAddress, notNullValue());
        assertThat(foundAddress.getName(), is("testAddress123"));
    }

    /**
     * Find by name test.
     */
    @Test
    public void findByNameTest() {
        Address foundAddress = AddressDAO.getInstance().findByName(addresses.get(0).getName());
        assertThat(foundAddress, notNullValue());
        assertThat(foundAddress.getName(), is("testAddress123"));
    }

    /**
     * Find all test.
     */
    @Test
    public void findAllTest() {
        Role role = TestDataFactory.createTestRole("testRole456");
        roles.add(role);
        User user = TestDataFactory.createTestUser("testUser456", "testPassword123", role);
        users.add(user);
        Address secondTestAddress = TestDataFactory.createTestAddress("testAddress456", user);
        addresses.add(secondTestAddress);
        List<? extends IEntity> all = AddressDAO.getInstance().findAll();
        assertThat(all.size(), greaterThanOrEqualTo(2));
    }

    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        Address testAddress = addresses.get(0);
        testAddress.setName("testAddress456");
        AddressDAO.getInstance().update(testAddress);
        Address foundAddress = AddressDAO.getInstance().findById(testAddress.getId());
        assertThat(foundAddress.getName(), is("testAddress456"));
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        boolean isDeleted = AddressDAO.getInstance().delete(addresses.get(0));
        assertTrue(isDeleted);
        assertThat(AddressDAO.getInstance().findByName("testAddress123"), nullValue());
    }
}