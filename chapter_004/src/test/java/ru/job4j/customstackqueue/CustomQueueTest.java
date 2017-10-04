package ru.job4j.customstackqueue;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Custom queue test.
 */
public class CustomQueueTest {

    /**
     * When push three then has three.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenPushThreeThenHasThree() throws Exception {
        CustomQueue<String> queue = new CustomQueue<>();

        queue.push("Bob");
        queue.push("John");
        queue.push("Tom");

        assertThat(queue.toString(), is("Bob, John, Tom"));
    }

    /**
     * When poll then delete and has this item.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenPollThenDeleteFirstAndHasThisItem() throws Exception {
        CustomQueue<String> queue = new CustomQueue<>();

        queue.push("Bob");
        queue.push("John");
        queue.push("Tom");

        String item = queue.poll();

        assertThat(queue.toString(), is("John, Tom"));
        assertThat(item, is("Bob"));
    }

    /**
     * When poll last then has it.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenPollLastThenHasIt() throws Exception {
        CustomQueue<String> queue = new CustomQueue<>();

        queue.push("Bob");
        queue.push("John");

        String firstItem = queue.poll();
        String lastItem = queue.poll();

        assertThat(lastItem, is("John"));
    }

    /**
     * When poll then previous element is last.
     */
    @Test
    public void whenPollThenNextElementIsFirst() {
        CustomQueue<String> queue = new CustomQueue<>();

        queue.push("Bob");
        queue.push("John");
        queue.push("Tom");

        queue.poll();

        assertThat(queue.getFirst(), is("John"));
    }

}