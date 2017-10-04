package ru.job4j.customlinkedlist;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Custom linked list test.
 */
public class CustomLinkedListTest {

    /**
     * When add three then string with specific format.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddThreeThenStringWithSpecificFormat() throws Exception {
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add("Bob");
        list.add("Tom");
        list.add("John");

        assertThat(list.toString(), is("Bob, Tom, John"));
    }

    /**
     * When get by id second item then second.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetByIdSecondItemThenSecond() throws Exception {
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add("Bob");
        list.add("Tom");
        list.add("John");

        assertThat(list.get(1).toString(), is("Tom"));
    }

    /**
     * When get first item then first.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetFirstItemThenFirst() throws Exception {
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add("Bob");
        list.add("Tom");
        list.add("John");

        assertThat(list.getFirst(), is("Bob"));
    }

    /**
     * When get last item then last.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetLastItemThenLast() throws Exception {
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add("Bob");
        list.add("Tom");
        list.add("John");

        assertThat(list.getLast(), is("John"));
    }

    /**
     * When three items then size three.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenThreeItemsThenSizeThree() throws Exception {
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add("Bob");
        list.add("Tom");
        list.add("John");

        assertThat(list.getSize(), is(3));
    }

    /**
     * When use iterator then correct iteration.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenUseIteratorThenCorrectIteration() throws Exception {
        CustomLinkedList<String> list = new CustomLinkedList<>();

        list.add("Bob");
        list.add("Tom");
        list.add("John");

        Iterator<String> it = list.iterator();
        boolean firstItem = true;
        String st = "";
        while (it.hasNext()) {
            if (firstItem) {
                st = it.next().toString();
            } else {
                st = String.format("%s, %s", st, it.next().toString());
            }
            firstItem = false;
        }

        assertThat(list.toString(), is("Bob, Tom, John"));
    }
}