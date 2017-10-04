package ru.job4j.customset;

import org.junit.Test;
import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * The type Simple set test.
 */
public class SimpleSetTest {
    /**
     * When add two then contains two.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddTwoThenContainsTwo() throws Exception {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("Tom");
        set.add("Bob");
        assertThat(set.getSize(), is(2));
    }

    /**
     * When add similar then contains unique.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddSimilarThenContainsUnique() throws Exception {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("Tom");
        set.add("Tom");

        Iterator<String> it = set.iterator();
        String st = "";
        if (it.hasNext()) {
            st = it.next();
        }

        assertThat(set.getSize(), is(1));
        assertThat(st, is("Tom"));
    }

    /**
     * When iterate three then has it.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenIterateThreeThenHasIt() throws Exception {
        SimpleSet<String> set = new SimpleSet<>();
        set.add("Tom");
        set.add("Bob");
        set.add("John");
        Iterator<String> it = set.iterator();
        int i = 0;
        String st = "";
        while (it.hasNext()) {
            if (i == 0) {
                st = String.format("%s", it.next());
            } else {
                st = String.format("%s, %s", st, it.next());
            }
            i++;
        }
        assertThat(st, is("Tom, Bob, John"));
    }

}