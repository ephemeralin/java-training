package ru.job4j.tracker;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ephemeralin on 21.05.17.
 */
public class TrackerTest {

    /**
     * When add new item then tracker has same item.
     * Test for methods: add, findAll
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "test item 1", "test description");
        tracker.add(item);
        assertThat(tracker.findAll()[0], is(item));
    }

    /**
     * When delete first item then tracker does not have any items.
     * Test for methods: add, delete
     */
    @Test
    public void whenDeleteFirstItemThenTrackerDoesNotHaveItems() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "test item 1", "test description");
        tracker.add(item);
        tracker.delete(item);
        assertThat(tracker.findAll().length, is(0));
    }

    /**
     * When find by name then tracker returns the item.
     * Test for methods: add, findByName
     */
    @Test
    public void whenFindByNameThenTrackerReturnsItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "test item 1", "test description");
        tracker.add(item);
        assertThat(tracker.findByName("test item 1")[0], is(item));
    }

    /**
     * When find by ID then tracker returns the item.
     * Test for methods: add, findByID
     */
    @Test
    public void whenFindByIDThenTrackerReturnsItem() {
        Tracker tracker = new Tracker();
        Item item = new Item("test1", "test item 1", "test description");
        tracker.add(item);
        assertThat(tracker.findByID("test1"), is(item));
    }



}
