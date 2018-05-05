package ru.job4j.musicplace.model.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import ru.job4j.musicplace.model.entity.Genre;
import ru.job4j.musicplace.model.entity.IEntity;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.greaterThanOrEqualTo;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * The type Genre dao test.
 */
public class GenreDAOTest {
    /**
     * Test genres list.
     */
    private List<Genre> genres = new ArrayList<>();

    /**
     * Before class test.
     */
    @BeforeClass
    public static void beforeClassTest() {
        MainRepository.getInstance();
    }

    /**
     * Before test.
     */
    @Before
    public void beforeTest() {
        deleteAllTestData();
        Genre genre = TestDataFactory.createTestGenre("testGenre123");
        genres.add(genre);
    }

    /**
     * After test.
     */
    @After
    public void afterTest() {
        deleteAllTestData();
    }

    /**
     * Delete all test data from DB.
     */
    private void deleteAllTestData() {
        for (Genre genre : genres) {
            GenreDAO.getInstance().delete(genre);
        }
    }

    /**
     * Create test.
     */
    @Test
    public void createTest() {
        assertNotEquals(0, genres.get(0).getId());
    }

    /**
     * Find by id test.
     */
    @Test
    public void findByIdTest() {
        Genre foundUser = GenreDAO.getInstance().findById(genres.get(0).getId());
        assertThat(foundUser.getName(), is("testGenre123"));
    }

    /**
     * Find by name test.
     */
    @Test
    public void findByNameTest() {
        Genre foundGenre = GenreDAO.getInstance().findByName(genres.get(0).getName());
        assertThat(foundGenre.getName(), is("testGenre123"));
    }

    /**
     * Find all test.
     */
    @Test
    public void findAllTest() {
        Genre secondTestGenre = TestDataFactory.createTestGenre("testGenre456");
        genres.add(secondTestGenre);
        List<? extends IEntity> all = GenreDAO.getInstance().findAll();
        assertThat(all.size(), greaterThanOrEqualTo(2));
    }

    /**
     * Update test.
     */
    @Test
    public void updateTest() {
        Genre testGenre = genres.get(0);
        testGenre.setName("testGenre456");
        GenreDAO.getInstance().update(testGenre);
        Genre foundGenre = GenreDAO.getInstance().findById(testGenre.getId());
        assertThat(foundGenre.getName(), is("testGenre456"));
    }

    /**
     * Delete test.
     */
    @Test
    public void deleteTest() {
        boolean isDeleted = GenreDAO.getInstance().delete(genres.get(0));
        assertTrue(isDeleted);
        assertNull(GenreDAO.getInstance().findByName("testGenre123"));
    }
}