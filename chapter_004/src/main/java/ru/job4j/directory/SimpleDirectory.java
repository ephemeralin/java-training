package ru.job4j.directory;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Simple directory.
 *
 * @param <K> the type parameter
 * @param <V> the type parameter
 */
public class SimpleDirectory<K, V> implements Iterable<K> {

    /**
     * The constant DEFAULT_SIZE.
     */
    static final int DEFAULT_SIZE = 3;

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
     * Returns index in the container.
     * @param h parameter for index calculation
     * @param length length of the container
     * @return index
     */
    private static int indexFor(int h, int length) {
        return h & (length - 1);
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

        if (key == null) {
            container[0].value = new Entry(0, value);
            isInserted = true;

        } else {
            if ((double) amountOfEntries / container.length > loadFactor) {
                //extend container
                container = Arrays.copyOf(container, container.length * 2);
            }
            int hash = hash(key.hashCode());
            int index = indexFor(hash, container.length);
            if (index < container.length) {
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

        if (key == null) {
            value = (V) container[0].value;

        } else {
            int hash = hash(key.hashCode());
            int index = indexFor(hash, container.length);
            if (index < container.length) {
                if (!(container[index] == null)) {
                    value = (V) container[index].value;
                }
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

        if (key == null) {
            container[0] = null;
            isDeleted = true;

        } else {
            int hash = hash(key.hashCode());
            int index = indexFor(hash, container.length);
            if (index < container.length) {
                if (!(container[index] == null)) {
                    container[index] = null;
                    isDeleted = true;
                }
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
    public Iterator<K> iterator() {
        return new Iterator<K>() {
            private int cursor = 0;

            @Override
            public boolean hasNext() {
                if (cursor == getSize()) {
                    return false;
                }
                while (cursor < getSize() && container[cursor] == null) {
                    cursor++;
                }
                return cursor != getSize();
            }

            @Override
            public K next() {
                int i = cursor;
                int size = getSize();
                if (i >= size) {
                    throw new NoSuchElementException();
                }

                cursor++;

                if (container[i] == null) {
                    throw new NoSuchElementException();
                }

                return (K) container[i].key;
            }
        };
    }
}
