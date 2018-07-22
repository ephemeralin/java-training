package ru.job4j.ioc.repos;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * The type Jdbc repository test.
 */
public class JdbcRepositoryTest {

    /**
     * Test genres list.
     */
    private List<User> users = new ArrayList<>();

    /**
     * Before test.
     */
    @Before
    public void beforeTest() {
        deleteAllTestData();
        User user = new User();
        user.setName("test name");
        int id = JdbcRepository.getInstance().create(user);
        user.setId(id);
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
            JdbcRepository.getInstance().delete(user);
        }
    }

    /**
     * Create.
     */
    @Test
    public void create() {
        assertNotNull(users.get(0));
    }

    /**
     * Find by id.
     */
    @Test
    public void findById() {
        User user = JdbcRepository.getInstance().findById(users.get(0).getId());
        assertThat(user.getName(), is(users.get(0).getName()));
    }

    /**
     * Update.
     */
    @Test
    public void update() {
        User user = users.get(0);

        user.setName("new name");
        JdbcRepository.getInstance().update(user);
        User updatedUser = JdbcRepository.getInstance().findById(user.getId());

        assertThat(updatedUser.getName(), is(user.getName()));
    }

    /**
     * Delete.
     */
    @Test
    public void delete() {
        User user = new User();
        user.setName("new test user");
        int id = JdbcRepository.getInstance().create(user);
        user.setId(id);

        boolean isDeleted = JdbcRepository.getInstance().delete(user);

        assertTrue(isDeleted);
    }
}