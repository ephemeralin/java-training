package ru.job4j.customlinkedlist;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Custom linked list.
 *
 * @param <E> the type parameter
 */
public class CustomLinkedList<E> implements Iterable<E> {

    /**
     * First node.
     */
    private Node<E> firstNode;
    /**
     * Last node.
     */
    private Node<E> lastNode;
    /**
     * Size of the list.
     */
    private int size = 0;
    /**
     * Next index.
     */
    private int nextIndex = 0;

    /**
     * Internal element of the List.
     * @param <E>
     */
    protected class Node<E> {
        /**
         * The Item.
         */
        private E item;
        /**
         * The Index.
         */
        private int index;
        /**
         * The Next.
         */
        private Node<E> next;
        /**
         * The Prev.
         */
        private Node<E> prev;

        /**
         * Instantiates a new Node.
         *
         * @param prev    the prev
         * @param element the element
         * @param next    the next
         * @param index   the index
         */
        Node(Node<E> prev, E element, Node<E> next, int index) {
            this.item = element;
            this.next = next;
            this.prev = prev;
            this.index = index;
        }

        /**
         * Gets item.
         *
         * @return the item
         */
        public E getItem() {
            return item;
        }

        /**
         * Sets item.
         *
         * @param item the item
         */
        public void setItem(E item) {
            this.item = item;
        }

        /**
         * Gets next.
         *
         * @return the next
         */
        public Node<E> getNext() {
            return next;
        }

        /**
         * Sets next.
         *
         * @param next the next
         */
        public void setNext(Node<E> next) {
            this.next = next;
        }

        /**
         * Gets prev.
         *
         * @return the prev
         */
        public Node<E> getPrev() {
            return prev;
        }

        /**
         * Sets prev.
         *
         * @param prev the prev
         */
        public void setPrev(Node<E> prev) {
            this.prev = prev;
        }
    }

    /**
     * Add.
     *
     * @param value the value
     */
    public void add(E value) {
        if (nextIndex == 0) {
            Node<E> newNode = new Node<E>(null, value, null, nextIndex);
            firstNode = newNode;
            lastNode = newNode;
        } else {
            Node<E> newNode = new Node<E>(lastNode, value, null, nextIndex);
            lastNode.next = newNode;
            lastNode = newNode;
        }
        nextIndex++;
        size++;
    }

    /**
     * Get e.
     *
     * @param index the index
     * @return the e
     */
    public E get(int index) {
        Node<E> node = firstNode;
        while (node != lastNode) {
            if (node.index == index) {
                return node.item;
            }
            node = node.next;
        }
        throw new NoSuchElementException();
    }

    /**
     * Gets first.
     *
     * @return the first
     */
    public E getFirst() {
        return firstNode.item;
    }

    /**
     * Gets last.
     *
     * @return the last
     */
    public E getLast() {
        return lastNode.item;
    }

    /**
     * Gets first node.
     *
     * @return the first node
     */
    public Node<E> getFirstNode() {
        return firstNode;
    }

    /**
     * Gets last node.
     *
     * @return the last node
     */
    public Node<E> getLastNode() {
        return lastNode;
    }

    /**
     * Sets first node.
     *
     * @param firstNode the first node
     */
    public void setFirstNode(Node<E> firstNode) {
        this.firstNode = firstNode;
    }

    /**
     * Sets last node.
     *
     * @param lastNode the last node
     */
    public void setLastNode(Node<E> lastNode) {
        this.lastNode = lastNode;
    }


    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        return size;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private Node<E> cursorNode = firstNode;

            @Override
            public boolean hasNext() {
                return !(cursorNode == null);
            }

            @Override
            public E next() {
                Node<E> n = cursorNode;
                if (n == null) {
                    throw new NoSuchElementException();
                }
                cursorNode = cursorNode.next;
                return n.item;
            }
        };
    }

    @Override
    public String toString() {
        String st = "";
        Iterator<E> it = this.iterator();
        boolean isFirst = true;
        while (it.hasNext()) {
            E element = it.next();
            if (isFirst) {
                st = element.toString();
                isFirst = false;
            } else {
                st = String.format("%s, %s", st, element.toString());
            }
        }
        return st;
    }
}