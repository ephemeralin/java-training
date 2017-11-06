package ru.job4j.customtree;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Tree test.
 */
public class TreeTest {

    /**
     * When add root and his child then has it.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddRootAndHisChildThenHasIt() throws Exception {
        Tree<String> orgChart = new Tree<String>("Director") {
            {
                add("Director", "Top manager");
            }
        };

        String result = orgChart.getChildren("Director").get(0).getValue();

        assertThat(result, is("Top manager"));
    }

    /**
     * When add two duplicates then has one.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddTwoDuplicatesThenHasOne() throws Exception {
        Tree<String> orgChart = new Tree<String>("Director") {
            {
                add("Director", "Top manager");
                add("Top manager", "Manager");
                add("Director", "Second Director");
                add("Second Director", "Manager");
            }
        };

        String expected = "[Director, Top manager, Manager, Second Director]";

        assertThat(orgChart.toString(), is(expected));
    }

    /**
     * When convert to list then has proper list.
     * toString() overrided method returns toString() for the result of toList() convert
     *
     * @throws Exception the exception
     */
    @Test
    public void whenConvertToListThenHasProperList() throws Exception {
        Tree<String> orgChart = new Tree<String>("Director") {
            {
                add("Director", "Top manager");
                add("Top manager", "Manager");
                add("Manager", "Worker 1");
                add("Manager", "Worker 2");
                add("Worker 2", "Sub Worker A");
                add("Worker 2", "Sub Worker B");
            }
        };

        String expected = "[Director, Top manager, Manager, Worker 1, Worker 2, Sub Worker A, Sub Worker B]";

        assertThat(orgChart.toString(), is(expected));
    }

    /**
     * Tesing Iterator function.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenIterateAllThenHasItAll() throws Exception {
        Tree<String> orgChart = new Tree<String>("Director") {
            {
                add("Director", "Top manager");
                add("Top manager", "Manager");
                add("Manager", "Worker 1");
                add("Manager", "Worker 2");
                add("Worker 2", "Sub Worker A");
                add("Worker 2", "Sub Worker B");
            }
        };

        String result = "";
        Iterator<String> iterator = orgChart.iterator();
        while (iterator.hasNext()) {
            result = String.format("%s, %s", result, iterator.next());
        }

        String expected = ", Director, Top manager, Manager, Worker 1, Worker 2, Sub Worker A, Sub Worker B";

        assertThat(result, is(expected));
    }

    /**
     * When tree is binary then true.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenTreeIsBinaryThenTrue() throws Exception {
        Tree<String> orgChart = new Tree<String>("Director") {
            {
                add("Director", "Top manager");
                add("Top manager", "Manager");
                add("Manager", "Worker 1");
                add("Manager", "Worker 2");
                add("Worker 2", "Sub Worker A");
                add("Worker 2", "Sub Worker B");
            }
        };

        assertThat(orgChart.isBinary(), is(true));
    }

    /**
     * When tree is not binary then false.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenTreeIsNotBinaryThenFalse() throws Exception {
        Tree<String> orgChart = new Tree<String>("Director") {
            {
                add("Director", "Top manager");
                add("Top manager", "Manager");
                add("Manager", "Worker 1");
                add("Manager", "Worker 2");
                add("Worker 2", "Sub Worker A");
                add("Worker 2", "Sub Worker B");

                add("Worker 2", "Sub Worker C");
            }
        };

        assertThat(orgChart.isBinary(), is(false));
    }
}