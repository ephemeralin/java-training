package ru.job4j.convertation;

import org.junit.Test;
import java.util.ArrayList;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Convert list test.
 */
public class ConvertListTest {

    /**
     * Test for Convert list of arrays to the list.
     *
     * @throws Exception the exception
     */
    @Test
    public void convert() throws Exception {
        ArrayList<Integer> expectedList = new ArrayList<>();
        for (int i = 1; i <= 6; i++) {
            expectedList.add(i);
        }

        ArrayList<int[]> list = new ArrayList<>();
        list.add(new int[] {1, 2});
        list.add(new int[] {3, 4, 5, 6});

        assertThat(new ConvertList().convert(list), is(expectedList));
    }

    /**
     * Test for To array when number of rows 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void toArrayWhenNumberOfRows3() throws Exception {
        int numberOfRows = 3;
        int[][] expectedArray = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            list.add(Integer.valueOf(i));
        }
        assertThat(new ConvertList().toArray(list, numberOfRows), is(expectedArray));
    }

    /**
     * Test for To list.
     *
     * @throws Exception the exception
     */
    @Test
    public void toList() throws Exception {
        int[][] array = {{1, 2, 3}, {4, 5, 6}, {7, 0, 0}};
        ArrayList<Integer> expectedList = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            expectedList.add(Integer.valueOf(i));
        }
        assertThat(new ConvertList().toList(array), is(expectedList));
    }

}