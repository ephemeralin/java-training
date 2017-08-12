package ru.job4j.convertation;

import java.util.ArrayList;
import java.util.List;


/**
 * The type Convert list.
 */
public class ConvertList {

    /**
     * Convertation from Array to ArrayList.
     *
     * @param array the array
     * @return the list
     */
    public List<Integer> toList(int[][] array) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                list.add(array[i][j]);
            }
        }
        while (true) {
            Integer element = list.get(list.size() - 1);
            if (element == Integer.valueOf(0)) {
                list.remove(element);
            } else {
                break;
            }
        }
        return list;
    }

    /**
     * To array int [ ] [ ].
     *
     * @param list the list
     * @param rows the rows
     * @return the int [ ] [ ]
     */
    public int[][] toArray(List<Integer> list, int rows) {
        int k = 0;
        int sizeOfList = list.size();
        int columns = list.size() / rows + 1;
        int[][] array = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                if (k < sizeOfList) {
                    Integer element = list.get(k);
                    array[i][j] = list.get(k);
                } else {
                    array[i][j] = 0;
                }
                k++;
            }
        }
        return array;
    }
}
