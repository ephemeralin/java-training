package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Engine;
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
 * The Engine repository test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class EngineRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EngineRepository repository;

    /**
     * Create test.
     */
    @Test
    public void createTest() {
        Engine engine = new Engine();
        engine.setName("diesel");
        engine = repository.save(engine);

        Engine engineResult = entityManager.find(Engine.class, engine.getId());
        assertThat(engine, is(engineResult));
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Engine engine = new Engine();
        engine.setName("diesel");
        int id = (int) entityManager.persistAndGetId(engine);

        Engine engineResult = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        assertThat(engineResult.getName(), is("diesel"));
    }

    /**
     * Find all test.
     */
    @Test
    public void findAllTest() {
        Engine engine = new Engine();
        engine.setName("gas");
        entityManager.persist(engine);
        Engine engine2 = new Engine();
        engine2.setName("diesel");
        entityManager.persist(engine2);

        List<Engine> all = repository.findAll();
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getName(), is("gas"));
        assertThat(all.get(1).getName(), is("diesel"));
    }

    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        Engine engine = new Engine();
        engine.setName("gas");
        int id = (int) entityManager.persistAndGetId(engine);
        engine.setId(id);

        engine.setName("diesel");
        repository.save(engine);
        Engine engineResult = entityManager.find(Engine.class, engine.getId());

        assertThat(engineResult.getName(), is("diesel"));
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        Engine engine = new Engine();
        engine.setName("diesel");
        int id = (int) entityManager.persistAndGetId(engine);
        engine.setId(id);

        repository.delete(engine);

        assertNull(entityManager.find(Engine.class, id));
    }
}
