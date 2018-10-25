package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Make;
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
 * The Make repository test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class MakeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private MakeRepository repository;

    /**
     * Create test.
     */
    @Test
    public void createTest() {
        Make make = new Make();
        make.setName("Honda");
        make = repository.save(make);

        Make makeResult = entityManager.find(Make.class, make.getId());
        assertThat(make, is(makeResult));
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Make make = new Make();
        make.setName("Honda");
        int id = (int) entityManager.persistAndGetId(make);

        Make makeResult = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        assertThat(makeResult.getName(), is("Honda"));
    }

    /**
     * Find all test.
     */
    @Test
    public void findAllTest() {
        Make make = new Make();
        make.setName("Toyota");
        entityManager.persist(make);
        Make make2 = new Make();
        make2.setName("Honda");
        entityManager.persist(make2);

        List<Make> all = repository.findAll();
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getName(), is("Toyota"));
        assertThat(all.get(1).getName(), is("Honda"));
    }

    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        Make make = new Make();
        make.setName("Toyota");
        int id = (int) entityManager.persistAndGetId(make);
        make.setId(id);

        make.setName("Honda");
        repository.save(make);
        Make makeResult = entityManager.find(Make.class, make.getId());

        assertThat(makeResult.getName(), is("Honda"));
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        Make make = new Make();
        make.setName("Honda");
        int id = (int) entityManager.persistAndGetId(make);
        make.setId(id);

        repository.delete(make);

        assertNull(entityManager.find(Make.class, id));
    }
}
