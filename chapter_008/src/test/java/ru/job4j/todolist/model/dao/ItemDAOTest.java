package ru.job4j.todolist.model.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ru.job4j.todolist.model.entity.Item;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertThat;

/**
 * The type Item dao test.
 */
public class ItemDAOTest {
    private Item item;
    private int id;

    /**
     * Before test.
     */
    @Before
    public void beforeTest() {
        item = new Item();
        item.setCreated(System.currentTimeMillis());
        item.setDescription("test description 123");
        item.setDone(false);
        id = ItemDAO.getInstance().create(item);
        item.setId(id);
    }

    /**
     * After test.
     */
    @After
    public void afterTest() {
        ItemDAO.getInstance().delete(id);
    }

    /**
     * Create.
     */
    @Test
    public void createTest() {
        assertThat(item.getId(), greaterThan(0));
    }

    /**
     * Find by id.
     */
    @Test
    public void findByIdTest() {
        final Item item = ItemDAO.getInstance().findById(id);
        assertThat(item.getId(), is(id));
    }

    /**
     * Find all.
     */
    @Test
    public void findAllTest() {
        final List<Item> list = ItemDAO.getInstance().findAll();
        assertThat(list.size(), greaterThan(0));
    }

    /**
     * Update.
     */
    @Test
    public void updateTest() {
        item.setDone(true);
        item.setDescription("test description 45678");

        ItemDAO.getInstance().update(item);
        Item itemUpdated = ItemDAO.getInstance().findById(id);

        assertThat(itemUpdated.isDone(), is(true));
        assertThat(itemUpdated.getDescription(), is("test description 45678"));
    }

    /**
     * Delete.
     */
    @Test
    public void deleteTest() {
        ItemDAO.getInstance().delete(id);
        Item item = ItemDAO.getInstance().findById(id);
        assertThat(item, nullValue());
    }
}