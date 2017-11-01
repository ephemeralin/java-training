package ru.job4j.customTree;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

public class TreeTest {
    @Test
    public void add() throws Exception {
        Tree<String> orgChart= new Tree<>("Director");

        orgChart.add("Director", "Top manager");
        orgChart.add("Top manager", "Worker");
        orgChart.add("Top manager", "Manager");

        assertThat(orgChart.toString(), is("134"));
    }

    @Test
    public void iterator() throws Exception {
    }

}