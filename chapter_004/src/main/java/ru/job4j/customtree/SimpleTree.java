package ru.job4j.customtree;

/**
 * The interface Simple tree.
 *
 * @param <E> the type parameter
 */
public interface SimpleTree<E extends Comparable<E>> extends Iterable<E> {
    /**
     * Adding child to the parent.
     *
     * @param parent parent.
     * @param child  child.
     * @return boolean
     */
    boolean add(E parent, E child);

    /**
     * Is binary or not.
     *
     * @return the boolean
     */
    boolean isBinary();
}
