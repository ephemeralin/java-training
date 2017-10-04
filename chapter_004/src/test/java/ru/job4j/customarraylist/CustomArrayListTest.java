package ru.job4j.customarraylist;

import org.junit.Test;

import java.util.Iterator;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Custom array list test.
 */
public class CustomArrayListTest {
    /**
     * When add two then contains two.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddTwoThenContainsTwo() throws Exception {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("Tom");
        list.add("Bob");
        assertThat(list.toString(), is("Tom, Bob"));
    }

    /**
     * When get first then has it.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetFirstThenHasIt() throws Exception {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("Tom");
        assertThat(list.get(0).toString(), is("Tom"));
    }

    /**
     * When iterate three then has it.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenIterateThreeThenHasIt() throws Exception {
        CustomArrayList<String> list = new CustomArrayList<>();
        list.add("Tom");
        list.add("Bob");
        list.add("John");
        Iterator<String> it = list.iterator();
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