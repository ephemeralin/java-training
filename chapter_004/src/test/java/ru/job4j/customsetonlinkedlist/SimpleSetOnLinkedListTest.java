package ru.job4j.customsetonlinkedlist;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Simple set on linked list test.
 */
public class SimpleSetOnLinkedListTest {

    /**
     * When add dublicated then contains only unique.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddDublicatedThenContainsOnlyUnique() throws Exception {
        SimpleSetOnLinkedList<String> set = new SimpleSetOnLinkedList<>();
        set.add("Tom");
        set.add("John");
        set.add("Tom");
        Iterator<String> it = set.iterator();

        String st = "";
        boolean first = true;
        while (it.hasNext()) {
            if (first) {
                st = it.next();
                first = false;
            } else {
                st = st + ", " + it.next();
            }
        }
        assertThat(st, is("Tom, John"));
    }

}