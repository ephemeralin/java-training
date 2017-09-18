package ru.job4j.doubleiterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Converter iterator.
 */
public class ConverterIterator {

    /**
     * Current inner Iterator.
     */
    private Iterator<Integer> currentInerIt;

    /**
     * Convert iterator.
     *
     * @param it the it
     * @return the iterator
     */
    Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {

            @Override
            public boolean hasNext() {
                boolean hasNext = false;

                if (currentInerIt.hasNext() || it.hasNext()) {
                    hasNext = true;
                }
                return hasNext;
            }

            @Override
            public Integer next() throws NoSuchElementException {
                Integer nextValue = null;
                if (currentInerIt == null && it.hasNext()) {
                    currentInerIt = it.next();
                    nextValue = next();
                } else if (currentInerIt.hasNext()) {
                    nextValue = currentInerIt.next();
                } else if (it.hasNext()) {
                    currentInerIt = it.next();
                    nextValue = next();
                } else {
                    throw new NoSuchElementException();
                }
                return nextValue;
            }
        };
    }
}
