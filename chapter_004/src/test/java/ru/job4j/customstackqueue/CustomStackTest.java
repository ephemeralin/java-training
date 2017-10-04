package ru.job4j.customstackqueue;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Custom stack test.
 */
public class CustomStackTest {
    /**
     * When push three then has three.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenPushThreeThenHasThree() throws Exception {
        CustomStack<String> stack = new CustomStack<>();

        stack.push("Bob");
        stack.push("John");
        stack.push("Tom");

        assertThat(stack.toString(), is("Bob, John, Tom"));
    }

    /**
     * When poll last then has it.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenPollLastThenHasIt() throws Exception {
        CustomStack<String> queue = new CustomStack<>();

        queue.push("Bob");
        queue.push("John");

        String firstItem = queue.poll();
        String lastItem = queue.poll();

        assertThat(lastItem, is("Bob"));
    }

    /**
     * When poll then delete and has this item.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenPollThenDeleteLastAndHasThisItem() throws Exception {
        CustomStack<String> stack = new CustomStack<>();

        stack.push("Bob");
        stack.push("John");
        stack.push("Tom");

        String item = stack.poll();

        assertThat(stack.toString(), is("Bob, John"));
        assertThat(item, is("Tom"));
    }

    /**
     * When poll then previous element is last.
     */
    @Test
    public void whenPollThenPreviousElementIsLast() {
        CustomStack<String> stack = new CustomStack<>();

        stack.push("Bob");
        stack.push("John");
        stack.push("Tom");

        stack.poll();

        assertThat(stack.getLast(), is("John"));
    }
}