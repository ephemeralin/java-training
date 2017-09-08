package ru.job4j.primes;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Primes with iterator.
 */
public class PrimesWithIterator implements Iterator<Integer> {

    /**
     * Is prime boolean.
     *
     * @param value the value
     * @return the boolean
     */
    static boolean isPrime(int value) {
        boolean isPrime = true;
        int maxDivider = (value % 2 == 0) ? value / 2 : (value + 1) / 2;
        for (int divider = 2; divider <= maxDivider; divider++) {
            if (value % divider == 0) {
                isPrime = false;
                break;
            }
        }
        return isPrime;
    }

    /**
     * Array of values.
     */
    private int[] values;

    /**
     * Next possible index of array.
     */
    private int nextIndex = 0;

    /**
     * Instantiates a new Primes with iterator.
     *
     * @param values the values
     */
    public PrimesWithIterator(int[] values) {
        this.values = values;
    }

    @Override
    public boolean hasNext() {
        boolean hasNext = false;
        if (nextIndex < values.length) {
            for (int i = nextIndex; i < values.length; i++) {
                if (isPrime(values[i])) {
                    hasNext = true;
                    break;
                }
            }
        }
        return hasNext;
    }

    @Override
    public Integer next() throws NoSuchElementException {
        if (nextIndex == values.length) {
            throw new NoSuchElementException();
        }
        for (int i = nextIndex; i < values.length; i++) {
            if (isPrime(values[i])) {
                nextIndex = i + 1;
                return values[i];
            }
        }
        throw new NoSuchElementException();
    }
}
