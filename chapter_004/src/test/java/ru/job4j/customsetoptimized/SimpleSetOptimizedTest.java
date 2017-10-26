package ru.job4j.customsetoptimized;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * The type Simple set test.
 */
public class SimpleSetOptimizedTest {
    /**
     * When add four then contains four sorted elements.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddFourThenContainsFourSortedElements() throws Exception {
        SimpleSetOptimized<String> set = new SimpleSetOptimized<>();
        set.add("Tom");
        set.add("Bob");
        set.add("Zak");
        set.add("July");

        assertThat(set.toString(), is("Bob, July, Tom, Zak"));
    }

    /**
     * When add one then contains only one.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddOneThenContainsOnlyOneElement() throws Exception {
        SimpleSetOptimized<String> set = new SimpleSetOptimized<>();
        set.add("Tom");

        assertThat(set.toString(), is("Tom"));
    }

    /**
     * When add similar then contains unique.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddSimilarThenContainsUnique() throws Exception {
        SimpleSetOptimized<String> set = new SimpleSetOptimized<>();
        set.add("Tom");
        set.add("Tom");

        assertThat(set.getSize(), is(1));
        assertThat(set.toString(), is("Tom"));
    }

    /**
     * When add similar then contains unique.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddSimilarPrimitiveThenContainsUniquePrimitive() throws Exception {
        SimpleSetOptimized<Integer> set = new SimpleSetOptimized<>();
        set.add(1);
        set.add(1);

        assertThat(set.getSize(), is(1));
        assertThat(set.toString(), is("1"));
    }

    /**
     * When iterate three then has it.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenIterateThreeThenHasIt() throws Exception {
        SimpleSetOptimized<String> set = new SimpleSetOptimized<>();
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
        assertThat(st, is("Bob, John, Tom"));
    }

}