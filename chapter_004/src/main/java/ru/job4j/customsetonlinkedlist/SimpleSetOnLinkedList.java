
package ru.job4j.customsetonlinkedlist;

import ru.job4j.customlinkedlist.CustomLinkedList;

import java.util.Iterator;

/**
 * The type Simple set.
 *
 * @param <E> the type parameter
 */
public class SimpleSetOnLinkedList<E> implements Iterable<E> {

    /**
     * Internal container of objects.
     */
    private CustomLinkedList<E> container;
    /**
     * The Next index.
     */
    private int nextIndex = 0;

    /**
     * Instantiates a new Simple set.
     */
    public SimpleSetOnLinkedList() {
        this.container = new CustomLinkedList<>();
    }

    /**
     * Add boolean.
     *
     * @param value the value
     * @return the boolean
     */
    public boolean add(E value) {
        boolean isAdded = true;
        Iterator<E> it = container.iterator();
        while (it.hasNext()) {
            E element = it.next();
            if (element.equals(value)) {
                isAdded = false;
                break;
            }
        }
        if (isAdded) {
            container.add(value);
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
        return container.iterator();
    }
}