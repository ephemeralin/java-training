package ru.job4j.customarraylist;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Custom array list.
 *
 * @param <E> the type parameter
 */
public class CustomArrayList<E> implements Iterable<E> {

    /**
     * The Default container size.
     */
    private static final int DEFAULT_CONTAINER_SIZE = 10;

    /**
     * Container of any Objects.
     */

    private Object[] container;

    /**
     * Next index.
     */
    private int nextIndex = 0;


    /**
     * Instantiates a new Custom array list.
     *
     * @param size the size
     */
    public CustomArrayList(int size) {
        this.container = new Object[size];
    }

    /**
     * Instantiates a new Custom array list.
     */
    public CustomArrayList() {
        this.container = new Object[DEFAULT_CONTAINER_SIZE];
    }

    /**
     * Add element in the end of the list.
     *
     * @param value the value
     */
    public void add(E value) {
        if (nextIndex >= container.length) {
            container = Arrays.copyOf(container, container.length * 2);
        }
        container[nextIndex] = value;
        nextIndex++;
    }

    /**
     * Get element by index.
     *
     * @param index the index
     * @return the e
     */
    public E get(int index) {
        if (index >= nextIndex) {
            throw new NoSuchElementException();
        }
        return (E) container[index];
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

    @Override
    public String toString() {
        String st = "";
        for (int i = 0; i < nextIndex; i++) {
            if (i == 0) {
                st = String.format("%s", container[i].toString());
            } else {
                st = String.format("%s, %s", st, container[i].toString());
            }
        }
        return st;
    }
}
