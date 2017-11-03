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
        Tree<String> orgChart = new Tree<>("Director");

        orgChart.add("Director", "Top manager");

        String result = orgChart.getChildren("Director").get(0).getValue();

        assertThat(result, is("Top manager"));
    }


    /**
     * When convert to list then has proper list.
     * toString() overrided method returns toString() for the result of toList() convert
     *
     * @throws Exception the exception
     */
    @Test
    public void whenConvertToListThenHasProperList() throws Exception {
        Tree<String> orgChart = new Tree<>("Director");

        orgChart.add("Director", "Top manager");
        orgChart.add("Top manager", "Manager");
        orgChart.add("Manager", "Worker 1");
        orgChart.add("Manager", "Worker 2");
        orgChart.add("Worker 2", "Sub Worker A");
        orgChart.add("Worker 2", "Sub Worker B");

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
        Tree<String> orgChart = new Tree<>("Director");

        orgChart.add("Director", "Top manager");
        orgChart.add("Top manager", "Manager");
        orgChart.add("Manager", "Worker 1");
        orgChart.add("Manager", "Worker 2");
        orgChart.add("Worker 2", "Sub Worker A");
        orgChart.add("Worker 2", "Sub Worker B");

        String result = "";
        Iterator<String> iterator = orgChart.iterator();
        while (iterator.hasNext()) {
            result = String.format("%s, %s", result, iterator.next());
        }

        String expected = ", Director, Top manager, Manager, Worker 1, Worker 2, Sub Worker A, Sub Worker B";

        assertThat(result, is(expected));
    }
}