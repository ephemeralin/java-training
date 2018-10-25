package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.User;
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
 * The User repository test.
 */
@RunWith(SpringRunner.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    /**
     * Create test.
     */
    @Test
    public void createTest() {
        User user = new User();
        user.setUsername("admin");
        user = repository.save(user);

        User userResult = entityManager.find(User.class, user.getId());
        assertThat(user, is(userResult));
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        User user = new User();
        user.setUsername("admin");
        int id = (int) entityManager.persistAndGetId(user);

        User userResult = repository.findById(id).orElseThrow(EntityNotFoundException::new);
        assertThat(userResult.getUsername(), is("admin"));
    }

    /**
     * Find all test.
     */
    @Test
    public void findAllTest() {
        User user = new User();
        user.setUsername("user");
        entityManager.persist(user);
        User user2 = new User();
        user2.setUsername("admin");
        entityManager.persist(user2);

        List<User> all = repository.findAll();
        assertThat(all.size(), is(2));
        assertThat(all.get(0).getUsername(), is("user"));
        assertThat(all.get(1).getUsername(), is("admin"));
    }

    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        User user = new User();
        user.setUsername("user");
        int id = (int) entityManager.persistAndGetId(user);
        user.setId(id);

        user.setUsername("admin");
        repository.save(user);
        User userResult = entityManager.find(User.class, user.getId());

        assertThat(userResult.getUsername(), is("admin"));
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        User user = new User();
        user.setUsername("admin");
        int id = (int) entityManager.persistAndGetId(user);
        user.setId(id);

        repository.delete(user);

        assertNull(entityManager.find(User.class, id));
    }
}
