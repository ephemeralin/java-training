package ru.job4j.tracker;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ephemeralin on 31.05.17.
 */
public class StubInputTest {

    /**
     * When add new item then tracker has same new item with the same name.
     * Test for methods: add, findAll, exit
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        String[] answers = {"0", "John Doe", "Test description", "6"};
        Input input = new StubInput(answers);
        new StartUI(tracker, input).init();
        assertThat(tracker.findAll().get(0).getDesc(), is("Test description"));
    }

    /**
     * When add new item and edit its desc then tracker has item with another desc.
     * Test for methods: add, findAll, edit, exit
     */
    @Test
    public void whenUserAddItemAndEditItsDescThenTrackerHasItemWithAnotherDesc() {
        Tracker tracker = new Tracker();
        String[] answers = {"0", "John Doe", "Test description", "2", "task0",
                "War and Peace", "6"};
        Input input = new StubInput(answers);
        new StartUI(tracker, input).init();
        assertThat(tracker.findAll().get(0).getDesc(), is("War and Peace"));
    }

    /**
     * When add two new items and delete first then tracker has only
     * second item.
     * Test for methods: add, findAll, findByID, delete, exit
     */
    @Test
    public void whenUserAddTwoItemsAndDeleteFirstThenTrackerHasOnlySecondOne() {
        Tracker tracker = new Tracker();
        String[] answers = {"0", "John Doe", "Test description 1", "0",
                "Bob Marley", "Test description 2", "3", "task0", "6"};
        Input input = new StubInput(answers);
        new StartUI(tracker, input).init();
        assertThat(tracker.findAll().get(0).getDesc(), is("Test description 2"));

    }

}

