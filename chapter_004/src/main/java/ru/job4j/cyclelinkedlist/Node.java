package ru.job4j.cyclelinkedlist;

import java.util.ArrayList;

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
     * @param currentNode the first
     * @return the boolean
     */
    static boolean hasCycle(Node currentNode) {
        ArrayList<Node> usedNodes = new ArrayList<>();
        boolean hasCycle = false;
        while (true) {
            if (currentNode.next == null) {
                break;
            }
            if (usedNodes.contains(currentNode)) {
                hasCycle = true;
                break;
            }
            usedNodes.add(currentNode);
            currentNode = currentNode.next;
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
