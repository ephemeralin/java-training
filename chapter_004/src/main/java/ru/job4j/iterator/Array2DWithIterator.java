package ru.job4j.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ephemeralin on 20.08.17.
 */
public class Array2DWithIterator implements Iterator {
    /**
     * 2D Array data.
     */
    private final int[][] values;
    /**
     * Current index of column.
     */
    private int indexColumn = 0;

    /**
     * Current index of row.
     */
    private int indexRow = 0;

    /**
     * Size of 2D array.
     */
    private int size = 0;

    /**
     * Instantiates a new Iterator 2 d array.
     *
     * @param values the values
     */
    public Array2DWithIterator(int[][] values) {
        this.values = values;
    }

    /**
     * Returns {@code true} if the iteration has more elements.
     * (In other words, returns {@code true} if {@link #next} would
     * return an element rather than throwing an exception.)
     *
     * @return {@code true} if the iteration has more elements
     */
    @Override
    public boolean hasNext() {
        return !(indexRow == this.values.length);
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     */
    @Override
    public Integer next() throws NoSuchElementException {

        int currentIndexRow = indexRow;
        int currentIndexColumn = indexColumn;

        if (indexColumn == values[indexRow].length - 1) {
            indexRow++;
            indexColumn = 0;
        } else {
            indexColumn++;
        }
        return values[currentIndexRow][currentIndexColumn];
    }
}
