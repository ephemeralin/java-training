package ru.job4j.customsetoptimized;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Simple set.
 *
 * @param <E> the type parameter
 */
public class SimpleSetOptimized<E> implements Iterable<E> {



    /**
     * Internal container of objects.
     */
    private E[] container;


    /**
     * Instantiates a new Simple set.
     */
    public SimpleSetOptimized() {
        this.container = (E[]) new Object[0];
    }

    /**
     * Add boolean.
     *
     * @param value the value
     * @return the boolean
     */
    public boolean add(E value) {
        boolean isAdded = true;

        for (int i = 0; i < container.length; i++) {
            if (container[i].equals(value)) {
                isAdded = false;
                break;
            }
        }

        if (isAdded) {
            int insertionPosition = findInsertionPosition(value);
            if (insertionPosition <= 0) {
                //element does not exist
                insertionPosition = insertionPosition * (-1) - 1;
                container = Arrays.copyOf(container, container.length + 1);
                int currentPosition = container.length - 2;
                while (currentPosition >= insertionPosition) {
                    container[currentPosition + 1] = container[currentPosition];
                    currentPosition--;
                }
                container[insertionPosition] = value;
            }
        }

        return isAdded;
    }

    /**
     * Find position for insertion.
     *
     * @param value value for insertion
     * @return number of position
     */
    public int findInsertionPosition(E value) {
//        if (this.getSize() == 0) {
//            return 0;
//        } else {
            return Arrays.binarySearch(container, value);
//        }
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return container.length;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != getSize();
            }

            @Override
            public E next() {
                int i = cursor;
                if (i >= getSize()) {
                    throw new NoSuchElementException();
                }
                cursor++;
                return (E) container[i];
            }
        };
    }

    @Override
    public String toString() {
        Iterator<E> it = this.iterator();
        int i = 0;
        String st = "";
        while (it.hasNext()) {
            if (i == 0) {
                st = String.format("%s", it.next());
            } else {
                st = String.format("%s, %s", st, it.next());
            }
            i++;
        }
        return st;
    }
}
