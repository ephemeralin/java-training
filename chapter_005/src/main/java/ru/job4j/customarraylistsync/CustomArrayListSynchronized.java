package ru.job4j.customarraylistsync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Custom array list.
 *
 * @param <E> the type parameter
 */
@ThreadSafe
public class CustomArrayListSynchronized<E> implements Iterable<E> {

    /**
     * The Default container size.
     */
    private static final int DEFAULT_CONTAINER_SIZE = 10;

    /**
     * Container of any Objects.
     */

    @GuardedBy("lock")
    private Object[] container;

    /**
     * Next index.
     */
    private int nextIndex = 0;

    /**
     * The Lock.
     */
    private Object lock = new Object();

    /**
     * Instantiates a new Custom array list.
     *
     * @param size the size
     */
    public CustomArrayListSynchronized(int size) {
        this.container = new Object[size];
    }

    /**
     * Instantiates a new Custom array list.
     */
    public CustomArrayListSynchronized() {
        this.container = new Object[DEFAULT_CONTAINER_SIZE];
    }

    /**
     * Add element in the end of the list.
     *
     * @param value the value
     */
    public void add(E value) {
        synchronized (lock) {
            if (nextIndex >= container.length) {
                container = Arrays.copyOf(container, container.length * 2);
            }
            container[nextIndex] = value;
            nextIndex++;
        }
    }

    /**
     * Get element by index.
     *
     * @param index the index
     * @return the e
     */
    public E get(int index) {
        synchronized (lock) {
            if (index >= nextIndex) {
                throw new NoSuchElementException();
            }
            return (E) container[index];
        }
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
