package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Transmission;
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
 * The Transmission repository test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class TransmissionRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private TransmissionRepository repository;

    /**
     * Create test.
     */
    @Test
    public void createTest() {
        Transmission transmission = new Transmission();
        transmission.setName("auto");
        transmission = repository.save(transmission);

        Transmission transmissionResult = entityManager.find(Transmission.class, transmission.getId());
        assertThat(transmission, is(transmissionResult));
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Transmission transmission = new Transmission();
        transmission.setName("auto");
        int id = (int) entityManager.persistAndGetId(transmission);

        Transmission transmissionResult = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        assertThat(transmissionResult.getName(), is("auto"));
    }

    /**
     * Find all test.
     */
    @Test
    public void findAllTest() {
        Transmission transmission = new Transmission();
        transmission.setName("manual");
        entityManager.persist(transmission);
        Transmission transmission2 = new Transmission();
        transmission2.setName("auto");
        entityManager.persist(transmission2);

        List<Transmission> all = repository.findAll();
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getName(), is("manual"));
        assertThat(all.get(1).getName(), is("auto"));
    }

    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        Transmission transmission = new Transmission();
        transmission.setName("manual");
        int id = (int) entityManager.persistAndGetId(transmission);
        transmission.setId(id);

        transmission.setName("auto");
        repository.save(transmission);
        Transmission transmissionResult = entityManager.find(Transmission.class, transmission.getId());

        assertThat(transmissionResult.getName(), is("auto"));
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        Transmission transmission = new Transmission();
        transmission.setName("auto");
        int id = (int) entityManager.persistAndGetId(transmission);
        transmission.setId(id);

        repository.delete(transmission);

        assertNull(entityManager.find(Transmission.class, id));
    }
}
