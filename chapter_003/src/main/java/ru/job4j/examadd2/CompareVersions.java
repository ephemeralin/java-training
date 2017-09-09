package ru.job4j.examadd2;

import java.util.Arrays;
import java.util.Comparator;

/**
 * The type Compare versions.
 */
public class CompareVersions {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int[][] array = {
                {2, 1},
                {2, 1, 0},
                {1, 1, 2}
        };

        Arrays.sort(array, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o2[0], o1[0]);
            }
        });

    }
}
