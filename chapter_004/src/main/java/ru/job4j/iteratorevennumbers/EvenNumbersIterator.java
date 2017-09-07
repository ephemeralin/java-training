package ru.job4j.iteratorevennumbers;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by ephemeralin on 20.08.17.
 */
public class EvenNumbersIterator implements Iterator {

    /**
     * Numbers.
     */
    private int[] numbers;

    /**
     * Current position.
     */
    private int nextPosition = 0;

    /**
     * Instantiates a new Iterator 2 d array.
     *
     * @param numbers the values
     */
    public EvenNumbersIterator(int[] numbers) {
        this.numbers = numbers;
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
        boolean hasNext = false;
        for (int i = nextPosition; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                hasNext = true;
            }
        }
        return hasNext;
    }

    /**
     * Returns the next element in the iteration.
     *
     * @return the next element in the iteration
     */
    @Override
    public Integer next() throws NoSuchElementException {
        if (nextPosition >= numbers.length) {
            throw new NoSuchElementException();
        }
        for (int i = nextPosition; i < numbers.length; i++) {
            if (numbers[i] % 2 == 0) {
                int next = numbers[i];
                nextPosition = i + 1;
                return next;
            }

        }
        throw new NoSuchElementException();
    }
}
