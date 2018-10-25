package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityNotFoundException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * The Car repository test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class CarRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CarRepository repository;

    /**
     * Create test.
     */
    @Test
    public void createTest() {
        Car car = new Car();
        car.setName("Honda");
        car = repository.save(car);

        Car carResult = entityManager.find(Car.class, car.getId());
        assertThat(car, is(carResult));
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Car car = new Car();
        car.setName("Honda");
        int id = (int) entityManager.persistAndGetId(car);

        Car bodyResult = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        assertThat(bodyResult.getName(), is("Honda"));
    }

    /**
     * Find all test.
     */
    @Test
    public void findAllTest() {
        Make make = new Make();
        make.setName("Honda");
        int makeId = (int) entityManager.persistAndGetId(make);
        make.setId(makeId);

        Model model = new Model();
        model.setName("Accord");
        model.setMake(make);
        int modelId = (int) entityManager.persistAndGetId(model);
        model.setId(modelId);

        Body body = new Body();
        body.setName("sedan");
        int bodyId = (int) entityManager.persistAndGetId(body);
        body.setId(bodyId);

        Transmission transmission = new Transmission();
        transmission.setName("manual");
        int transmissionId = (int) entityManager.persistAndGetId(transmission);
        transmission.setId(transmissionId);

        Engine engine = new Engine();
        engine.setName("gas");
        int engineId = (int) entityManager.persistAndGetId(engine);
        engine.setId(engineId);

        User user = new User();
        user.setUsername("admin");
        int userId = (int) entityManager.persistAndGetId(user);
        user.setId(userId);

        Car car = new Car();
        car.setName("Honda Accord 123");
        car.setMake(make);
        car.setModel(model);
        car.setBody(body);
        car.setTransmission(transmission);
        car.setEngine(engine);
        car.setOwner(user);
        entityManager.persist(car);

        List<Car> all = repository.findAll();
        assertThat(all.size(), is(1));
        assertThat(all.get(0).getName(), is("Honda Accord 123"));
    }

    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        Car car = new Car();
        car.setName("Honda");
        int id = (int) entityManager.persistAndGetId(car);
        car.setId(id);

        car.setName("Toyota");
        repository.save(car);
        Car carResult = entityManager.find(Car.class, car.getId());

        assertThat(carResult.getName(), is("Toyota"));
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        Car car = new Car();
        car.setName("Honda");
        int id = (int) entityManager.persistAndGetId(car);
        car.setId(id);

        repository.delete(car);

        assertNull(entityManager.find(Car.class, id));
    }
}