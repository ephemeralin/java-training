package ru.job4j.carplace.model.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.carplace.model.entity.Role;
import ru.job4j.carplace.model.entity.User;

import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * The type User dao test.
 */
public class UserDAOTest {

    private final UserDAO dao = UserDAO.getInstance();
    private final RoleDAO roleDao = RoleDAO.getInstance();
    private User entity;
    private Role role;

    /**
     * Prepare test data.
     *
     * @throws SQLException the sql exception
     */
    @Before
    public void prepareTestData() throws SQLException {
        entity = new User();
        entity.setLogin("test");
        entity.setPassword("pass");
        role = new Role();
        role.setName("role");
        roleDao.create(role);
        entity.setRole(role);
        dao.create(entity);
    }

    /**
     * Clean up test data.
     */
    @After
    public void cleanUpTestData() {
        dao.delete(entity.getId());
        roleDao.delete(role.getId());
    }

    /**
     * Create.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void createTest() throws SQLException {
        assertThat(entity, is(dao.findById(entity.getId())));
    }

    /**
     * Find all.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void findAllTest() throws SQLException {
        assertTrue(dao.findAll().contains(entity));
    }

    /**
     * Update.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void updateTest() throws SQLException {
        entity.setLogin("new test");
        dao.update(entity);
        assertThat(dao.findById(entity.getId()).getLogin(), is(entity.getLogin()));
    }

    /**
     * Delete.
     *
     * @throws SQLException the sql exception
     */
    @Test
    public void deleteTest() throws SQLException {
        dao.delete(entity.getId());
        assertTrue(dao.findAll().isEmpty());
    }

    /**
     * Is identified.
     */
    @Test
    public void isIdentified() {
        assertTrue(dao.isIdentified(entity.getLogin(), entity.getPassword()));
    }

    /**
     * Gets role by login.
     */
    @Test
    public void getRoleByLogin() {
        assertThat(dao.getRoleByLogin(entity.getLogin()), is(role));
    }

    /**
     * Find by login.
     */
    @Test
    public void findByLogin() {
        assertThat(dao.findByLogin(entity.getLogin()), is(entity));
    }
}