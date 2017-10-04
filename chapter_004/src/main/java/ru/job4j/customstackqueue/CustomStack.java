package ru.job4j.customstackqueue;

import ru.job4j.customlinkedlist.CustomLinkedList;

/**
 * The type Custom stack.
 *
 * @param <T> the type parameter
 */
public class CustomStack<T> extends CustomLinkedList<T> {
    /**
     * Instantiates a new Custom stack.
     */
    public CustomStack() {
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
        CustomLinkedList.Node node = super.getLastNode();
        T value = (T) node.getItem();
        Node<T> prevNode = node.getPrev();
        if (prevNode != null) {
            prevNode.setNext(null);
        }
        super.setLastNode(prevNode);
        return value;
    }
}