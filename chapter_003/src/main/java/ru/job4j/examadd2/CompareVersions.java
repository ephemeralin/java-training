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
                int maxLength = (o1.length > o2.length) ? o1.length : o2.length;
                int compareResult = 0;
                for (int i = 0; i < maxLength; i++) {
                    if (i >= o2.length) {
                        compareResult = 1;
                    } else if (i >= o1.length) {
                        compareResult = -1;
                    } else {
                        compareResult = Integer.compare(o1[i], o2[i]);
                    }
                    if (compareResult != 0) {
                        break;
                    }
                }
                return compareResult;
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
            public int compare(int[] o2, int[] o1) {
                int maxLength = (o1.length > o2.length) ? o1.length : o2.length;
                int compareResult = 0;
                for (int i = 0; i < maxLength; i++) {
                    if (i >= o2.length) {
                        compareResult = 1;
                    } else if (i >= o1.length) {
                        compareResult = -1;
                    } else {
                        compareResult = Integer.compare(o1[i], o2[i]);
                    }
                    if (compareResult != 0) {
                        break;
                    }
                }
                return compareResult;
            }
        });

        return array;
    }

}
