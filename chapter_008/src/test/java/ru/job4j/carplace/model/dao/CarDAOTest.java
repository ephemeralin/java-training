package ru.job4j.carplace.model.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.carplace.model.entity.*;

import java.sql.SQLException;
import java.util.Calendar;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * The type Car dao test.
 */
public class CarDAOTest {

    private final CarDAO dao = CarDAO.getInstance();
    private Car entity;

    /**
     * Prepare test data.
     *
     * @throws SQLException the sql exception
     */
    @Before
    public void prepareTestData() throws SQLException {
        entity = new Car();
        entity.setName("test");
        entity.setSold(false);
        Make make = new Make();
        make.setName("test make");
        entity.setMake(make);
        Model model = new Model();
        model.setName("test model");
        model.setMake(make);
        ModelDAO.getInstance().create(model);
        entity.setModel(model);
        Body body = new Body();
        body.setName("test body");
        BodyDAO.getInstance().create(body);
        entity.setBody(body);
        Engine engine = new Engine();
        engine.setName("test engine");
        EngineDAO.getInstance().create(engine);
        entity.setEngine(engine);
        Transmission transmission = new Transmission();
        transmission.setName("test transmission");
        entity.setTransmission(transmission);
        TransmissionDAO.getInstance().create(transmission);
        Role role = new Role();
        role.setName("test role");
        RoleDAO.getInstance().create(role);
        User user = new User();
        user.setLogin("test user");
        user.setRole(role);
        UserDAO.getInstance().create(user);
        entity.setOwner(user);
//        entity.setImage(null);
        entity.setBase64imageFile("");
        Long date = Calendar.getInstance().getTimeInMillis();
        entity.setDate(date);
        dao.create(entity);
    }

    /**
     * Clean up test data.
     */
    @After
    public void cleanUpTestData() {
        this.dao.delete(entity.getId());
    }

    /**
     * Create test.
     */
    @Test
    public void createTest() {
        assertThat(entity, is(dao.findById(entity.getId())));
    }

    /**
     * Find all.
     */
    @Test
    public void findAll() {
        assertTrue(dao.findAll().contains(entity));
    }

    /**
     * Find today.
     */
    @Test
    public void findToday() {
        assertTrue(dao.findToday().contains(entity));
    }

    /**
     * Update.
     */
    @Test
    public void update() {
        entity.setName("new name");
        dao.getInstance().update(entity);
        assertThat(dao.findById(entity.getId()).getName(), is(entity.getName()));
    }

    /**
     * Delete.
     */
    @Test
    public void delete() {
        dao.delete(entity.getId());
        assertTrue(dao.findAll().isEmpty());
    }
}