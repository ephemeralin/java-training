package ru.job4j.cyclelinkedlist;

/**
 * The type Node.
 *
 * @param <T> the type parameter
 */
public class Node<T> {
    /**
     * Value of list.
     */
    private T value;
    /**
     * Next element of the list.
     */
    private Node<T> next;

    /**
     * Instantiates a new Node.
     *
     * @param value the value
     */
    public Node(T value) {
        this.value = value;
    }

    /**
     * Has cycle boolean.
     *
     * @param first the first
     * @return the boolean
     */
    static boolean hasCycle(Node first) {
        Node current = first;
        boolean hasCycle = false;
        while (true) {
            if (current.next == null) {
                break;
            }
            if (current.next == first) {
                hasCycle = true;
                break;
            }
            current = current.next;
        }
        return hasCycle;
    }

    /**
     * Sets next.
     *
     * @param next the next
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
