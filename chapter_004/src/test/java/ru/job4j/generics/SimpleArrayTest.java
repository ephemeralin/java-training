package ru.job4j.generics;

import org.junit.Test;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Simple array test.
 */
public class SimpleArrayTest {
    /**
     * When add one element then has first one.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddOneElementThenHasFirstOne() throws Exception {
        SimpleArray<String> arrayOfStrings = new SimpleArray<>(1);
        arrayOfStrings.add("one");
        assertThat(arrayOfStrings.get(0), is("one"));
    }

    /**
     * When has three item then return second.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenHasThreeItemThenReturnSecond() throws Exception {
        SimpleArray<String> arrayOfStrings = new SimpleArray<>(3);
        arrayOfStrings.add("one");
        arrayOfStrings.add("two");
        arrayOfStrings.add("three");
        assertThat(arrayOfStrings.get(1), is("two"));
    }

    /**
     * When update then has new.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenUpdateThenHasNew() throws Exception {
        SimpleArray<String> arrayOfStrings = new SimpleArray<>(1);
        arrayOfStrings.add("one");
        arrayOfStrings.update("two", 0);
        assertThat(arrayOfStrings.get(0), is("two"));
    }

    /**
     * When delete then has null.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenDeleteThenHasNull() throws Exception {
        SimpleArray<String> arrayOfStrings = new SimpleArray<>(1);
        arrayOfStrings.add("one");
        arrayOfStrings.delete(0);
        assertNull(arrayOfStrings.get(0));
    }

}