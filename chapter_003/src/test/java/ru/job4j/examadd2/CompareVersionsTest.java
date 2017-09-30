package ru.job4j.examadd2;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Compare versions test.
 */
public class CompareVersionsTest {
    /**
     * Sort.
     *
     * @throws Exception the exception
     */
    @Test
    public void sort() throws Exception {
        int[][] originalArray = {
                {2, 1},
                {2, 1, 0},
                {3, 1, 1},
                {1, 1, 2}
        };
        int[][] expectedArray = {
                {1, 1, 2},
                {2, 1},
                {2, 1, 0},
                {3, 1, 1}
        };
        assertThat(CompareVersions.sort(originalArray), is(expectedArray));
    }

    /**
     * Sort reversed.
     *
     * @throws Exception the exception1
     */
    @Test
    public void sortReversed() throws Exception {
        int[][] originalArray = {
                {2, 1, 0},
                {1, 1, 2},
                {2, 1}
        };
        int[][] expectedArray = {
                {2, 1, 0},
                {2, 1},
                {1, 1, 2}
        };
        assertThat(CompareVersions.sortReversed(originalArray), is(expectedArray));
    }

}