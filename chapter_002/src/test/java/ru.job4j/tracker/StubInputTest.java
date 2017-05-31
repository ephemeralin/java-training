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
     * Test for methods: add, findAll
     */
    @Test
    public void whenUserAddItemThenTrackerHasNewItemWithSameName() {
        Tracker tracker = new Tracker();
        String[] answers = {"0", "John Doe", "Test description", "6"};
        Input input = new StubInput(answers);
        new StartUI(tracker, input).init();
        assertThat(tracker.findAll()[0].getDesc(), is("Test description"));

    }

}

