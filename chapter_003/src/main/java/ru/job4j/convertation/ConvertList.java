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

        return list;
    }

    /**
     * Convertation from ArrayList to Array.
     *
     * @param list the list
     * @param rows the rows
     * @return the array
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
                    if (element == null) {
                        array[i][j] = 0;
                    } else {
                        array[i][j] = list.get(k);
                    }
                } else {
                    array[i][j] = 0;
                }
                k++;
            }
        }
        return array;
    }

    /**
     * Convert list of arrays to the list.
     *
     * @param list the list of arrays
     * @return the list
     */
    public List<Integer> convert(List<int[]> list) {
        ArrayList<Integer> resultList = new ArrayList<>();
        for (int[] elementOfList : list) {
            for (int elementOfArray : elementOfList) {
                resultList.add(elementOfArray);
            }
        }
        return resultList;
    }
}
