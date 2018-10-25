package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Body;
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
 * The Body repository test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class BodyRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private BodyRepository repository;

    /**
     * Create test.
     */
    @Test
    public void createTest() {
        Body body = new Body();
        body.setName("sedan");
        body = repository.save(body);

        Body bodyResult = entityManager.find(Body.class, body.getId());
        assertThat(body, is(bodyResult));
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Body body = new Body();
        body.setName("sedan");
        int id = (int) entityManager.persistAndGetId(body);

        Body bodyResult = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        assertThat(bodyResult.getName(), is("sedan"));
    }

    /**
     * Find all test.
     */
    @Test
    public void findAllTest() {
        Body body1 = new Body();
        body1.setName("sedan");
        entityManager.persist(body1);
        Body body2 = new Body();
        body2.setName("hatchback");
        entityManager.persist(body2);

        List<Body> all = repository.findAll();
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getName(), is("sedan"));
        assertThat(all.get(1).getName(), is("hatchback"));
    }

    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        Body body = new Body();
        body.setName("sedan");
        int id = (int) entityManager.persistAndGetId(body);
        body.setId(id);

        body.setName("hatchback");
        repository.save(body);
        Body bodyResult = entityManager.find(Body.class, body.getId());

        assertThat(bodyResult.getName(), is("hatchback"));
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        Body body = new Body();
        body.setName("sedan");
        int id = (int) entityManager.persistAndGetId(body);
        body.setId(id);

        repository.delete(body);

        assertNull(entityManager.find(Body.class, id));
    }
}