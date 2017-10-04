package ru.job4j.customset;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Simple set.
 *
 * @param <E> the type parameter
 */
public class SimpleSet<E> implements Iterable<E> {

    /**
     * The Default container size.
     */
    private static final int DEFAULT_CONTAINER_SIZE = 10;


    /**
     * Internal container of objects.
     */
    private Object[] container;
    /**
     * The Next index.
     */
    private int nextIndex = 0;

    /**
     * Instantiates a new Simple set.
     *
     * @param size the size
     */
    public SimpleSet(int size) {
        this.container = new Object[size];
    }

    /**
     * Instantiates a new Simple set.
     */
    public SimpleSet() {
        this.container = new Object[DEFAULT_CONTAINER_SIZE];
    }

    /**
     * Add boolean.
     *
     * @param value the value
     * @return the boolean
     */
    public boolean add(E value) {
        boolean isAdded = true;
        for (int i = 0; i < nextIndex; i++) {
            if (container[i].equals(value)) {
                isAdded = false;
                break;
            }
        }
        if (isAdded) {
            if (nextIndex >= container.length) {
                container = Arrays.copyOf(container, container.length * 2);
            }
            container[nextIndex] = value;
            nextIndex++;
        }
        return isAdded;
    }

    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return nextIndex;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                return cursor != nextIndex;
            }

            @Override
            public E next() {
                int i = cursor;
                if (i >= nextIndex) {
                    throw new NoSuchElementException();
                }
                cursor++;
                return (E) container[i];
            }
        };
    }
}
