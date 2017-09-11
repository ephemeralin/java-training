package ru.job4j.examadd2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The type Compare versions.
 */
public class CompareVersions {

    /**
     * Sort 2D-array.
     *
     * @param array the array
     * @return the sorted 2D-array
     */
    public static int[][] sort(int[][] array) {

        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[0], o2[0]);
            }
        });

        return array;
    }

    /**
     * Reversed Sort 2D-array.
     *
     * @param array the array
     * @return the sorted 2D-array
     */
    public static int[][] sortReversed(int[][] array) {

        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0], o1[0]);
            }
        });

        return array;
    }

}
