package ru.job4j.directory;

import java.util.Iterator;

public class SimpleDirectory<K, V> implements Iterable<V>{

    public boolean insert(K key, V value) {
        return false;
    }

    public V get(K key) {
        return null;
    }

    public boolean delete(K key) {
        return false;
    }

    @Override
    public Iterator<V> iterator() {
        return new Iterator<V>() {
            @Override
            public boolean hasNext() {
                return false;
            }

            @Override
            public V next() {
                return null;
            }
        };
    }
}
