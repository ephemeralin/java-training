package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Make;
import com.ephemeralin.carplace.model.Model;
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
 * The Model repository test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class ModelRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private ModelRepository repository;

    /**
     * Create test.
     */
    @Test
    public void createTest() {
        Model model = new Model();
        model.setName("Accord");
        model = repository.save(model);

        Model modelResult = entityManager.find(Model.class, model.getId());
        assertThat(model, is(modelResult));
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Model model = new Model();
        model.setName("Accord");
        int id = (int) entityManager.persistAndGetId(model);

        Model modelResult = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        assertThat(modelResult.getName(), is("Accord"));
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
        model.setName("CR-V");
        model.setMake(make);
        entityManager.persist(model);
        Model model2 = new Model();
        model2.setName("Accord");
        model2.setMake(make);
        entityManager.persist(model2);

        List<Model> all = repository.findAll();
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getName(), is("CR-V"));
        assertThat(all.get(1).getName(), is("Accord"));
    }

    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        Model model = new Model();
        model.setName("CR-V");
        int id = (int) entityManager.persistAndGetId(model);
        model.setId(id);

        model.setName("Accord");
        repository.save(model);
        Model modelResult = entityManager.find(Model.class, model.getId());

        assertThat(modelResult.getName(), is("Accord"));
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        Model model = new Model();
        model.setName("Accord");
        int id = (int) entityManager.persistAndGetId(model);
        model.setId(id);

        repository.delete(model);

        assertNull(entityManager.find(Model.class, id));
    }
}
