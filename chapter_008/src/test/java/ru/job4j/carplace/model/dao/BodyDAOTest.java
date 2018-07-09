package ru.job4j.carplace.model.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.carplace.model.entity.Body;

import java.sql.SQLException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * The type Body dao test.
 */
public class BodyDAOTest {

    private final BodyDAO dao = BodyDAO.getInstance();
    private Body entity;

    /**
     * Prepare test data.
     *
     * @throws SQLException the sql exception
     */
    @Before
    public void prepareTestData() throws SQLException {
        this.entity = new Body();
        this.entity.setName("test");
        this.dao.create(entity);
    }

    /**
     * Clean up test data.
     */
    @After
    public void cleanUpTestData() {
        this.dao.delete(entity.getId());
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
        entity.setName("new test");
        dao.update(entity);
        assertThat(dao.findById(entity.getId()).getName(), is(entity.getName()));
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
}