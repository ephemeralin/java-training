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
        assertThat(tracker.findAll().get(0).getName(), is("test item 1"));
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
        assertThat(tracker.findAll().size(), is(0));
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
        assertThat(tracker.findByName("test item 1").get(0), is(item));
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

    /**
     * When update Item then item is updated.
     * Test for methods: add, update
     */
    @Test
    public void whenUpdateItemThenTrackerReturnsItem() {
        Tracker tracker = new Tracker();

        Item item1 = new Item("test1", "test item 1", "test description 1");
        tracker.add(item1);

        Item item2 = new Item("test2", "test item 2", "test description 2");
        tracker.add(item2);

        Item itemUpdated = new Item("test2", "updated item", "updated description");
        tracker.update(itemUpdated);

        assertThat(tracker.findAll().get(1).getName(), is("updated item"));
    }


}
