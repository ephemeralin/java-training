package ru.job4j.directory;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Simple directory.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class SimpleDirectory<K, V> implements Iterable<V> {

    /**
     * The constant DEFAULT_SIZE. Must be even
     */
    static final int DEFAULT_SIZE = 4;

    /**
     * The constant DEFAULT_LOAD_FACTOR.
     */
    static final double DEFAULT_LOAD_FACTOR = 0.6;

    /**
     * Amount of entries. Uses for checking loading of the container.
     */
    private int amountOfEntries = 0;

    /**
     * Load factor. Shows loading degree of the container.
     */
    private double loadFactor;

    /**
     * The container.
     */
    private Entry[] container;

    /**
     * Instantiates a new Simple directory.
     */
    public SimpleDirectory() {
        this.container = new Entry[SimpleDirectory.DEFAULT_SIZE];
        this.loadFactor = SimpleDirectory.DEFAULT_LOAD_FACTOR;
    }

    /**
     * Hash int.
     *
     * @param key the key
     * @return the int
     */
    static final int hash(Object key) {
        int h;
        return (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16);
    }


    /**
     * Extends container. New length must be even.
     */
    private void extendContainer() {
        int newLength = container.length * 2;
        newLength = (newLength % 2 != 0) ? newLength - 1 : newLength;
        Entry<K, V>[] t = new Entry[newLength];
        for (Entry<K, V> e : container) {
            if (e != null) {
                int index = indexFor(e.key);
                t[index] = e;
            }
        }
        container = t;
    }

    /**
     * Calculates index for the Key in the Container.
     * @param key the key for which index should be calculated
     * @return the calculated index
     */
    private int indexFor(K key) {
        int hash = hash(key.hashCode());
        return hash % this.container.length;
    }

    /**
     * Insert value for key.
     *
     * @param key   the key
     * @param value the value
     * @return true if value inserted
     */
    public boolean insert(K key, V value) {
        boolean isInserted = false;

        if (key != null) {
            if ((double) amountOfEntries / container.length > loadFactor) {
                extendContainer();
            }
            int index = indexFor(key);
            Entry<K, V> entry = container[index];
            if (entry == null) {
                //add new
                Entry newEntry = new Entry(key, value);
                container[index] = newEntry;
                amountOfEntries++;
            } else if (entry.key.equals(key)) {
                //update
                entry.value = value;
            }
        }
        return isInserted;
    }

    /**
     * Get value for key.
     *
     * @param key the key
     * @return the value
     */
    public V get(K key) {
        V value = null;

        if (key != null) {
            int index = indexFor(key);
            if (container[index] != null) {
                value = (V) container[index].value;
            }
        }
        return value;
    }

    /**
     * Delete value for key.
     *
     * @param key the key
     * @return true if deleted else false
     */
    public boolean delete(K key) {
        boolean isDeleted = false;

        if (key != null) {
            int index = indexFor(key);
            if (container[index] != null) {
                container[index] = null;
                isDeleted = true;
                amountOfEntries--;
            }
        }
        return isDeleted;
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
    public String toString() {
        boolean first = true;
        String st = "";
        Iterator it = this.iterator();
        for (int i = 0; i < container.length; i++) {
            Entry entry = container[i];
            if (entry == null) {
                continue;
            }
            if (first) {
                st = String.format("%s", entry.toString());
                first = false;
            } else {
                st = String.format("%s, %s", st, entry.toString());
            }
        }
        return st;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            private int cursor = 0;
            private int amountOfReturned = 0;

            @Override
            public boolean hasNext() {
                return amountOfReturned < amountOfEntries;
            }

            @Override
            public V next() {
                int i = cursor;
                int size = getSize();

                if (i >= size) {
                    throw new NoSuchElementException();
                }

                while (i < size && container[i] == null) {
                    i++;
                    cursor++;
                }

                if (i >= size) {
                    throw new NoSuchElementException();
                }

                amountOfReturned++;
                cursor++;

                return (V) container[i].value;
            }
        };
    }

    /**
     * The type Entry.
     *
     * @param <K> the type parameter
     * @param <V> the type parameter
     */
    public class Entry<K, V> {
        /**
         * Key.
         */
        private K key;
        /**
         * Value.
         */
        private V value;

        /**
         * Instantiates a new Entry.
         *
         * @param key   the key
         * @param value the value
         */
        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String toString() {
            return String.format("(%s, %s)", key, value);
        }
    }
}
