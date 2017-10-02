package ru.job4j.customstackqueue;

import ru.job4j.customlinkedlist.CustomLinkedList;

/**
 * The type Custom stack.
 *
 * @param <T> the type parameter
 */
public class CustomQueue<T> extends CustomLinkedList<T> {
    /**
     * Instantiates a new Custom stack.
     */
    public CustomQueue() {
    }

    /**
     * Push.
     *
     * @param value the value
     */
    public void push(T value) {
        super.add(value);
    }

    /**
     * Poll t.
     *
     * @return the t
     */
    public T poll() {
        CustomLinkedList.Node node = super.getFirstNode();
        T value = (T) node.getItem();
        node.getNext().setPrev(null);
        super.setFirstNode(node.getNext());
        return value;
    }
}
