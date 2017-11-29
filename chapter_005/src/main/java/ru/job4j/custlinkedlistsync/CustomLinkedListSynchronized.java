package ru.job4j.custlinkedlistsync;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * The type Custom linked list.
 *
 * @param <E> the type parameter
 */
@ThreadSafe
public class CustomLinkedListSynchronized<E> implements Iterable<E> {

    /**
     * The Lock.
     */
    private Object lock = new Object();

    /**
     * First node.
     */
    @GuardedBy("lock")
    private Node<E> firstNode;
    /**
     * Last node.
     */
    @GuardedBy("lock")
    private Node<E> lastNode;
    /**
     * Size of the list.
     */
    @GuardedBy("lock")
    private int size = 0;
    /**
     * Next index.
     */
    @GuardedBy("lock")
    private int nextIndex = 0;

    /**
     * Internal element of the List.
     * @param <E>
     */
    @ThreadSafe
    protected class Node<E> {
        /**
         * The Node's Lock.
         */
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
    @GuardedBy("lock")
    public void add(E value) {
        synchronized (lock) {
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
    }

    /**
     * Get e.
     *
     * @param index the index
     * @return the e
     */
    public E get(int index) {
        synchronized (lock) {
            Node<E> node = firstNode;
            while (node != lastNode) {
                if (node.index == index) {
                    return node.item;
                }
                node = node.next;
            }
            throw new NoSuchElementException();
        }
    }

    /**
     * Gets first.
     *
     * @return the first
     */
    public E getFirst() {
        synchronized (lock) {
            return firstNode.item;
        }
    }

    /**
     * Gets last.
     *
     * @return the last
     */
    public E getLast() {
        synchronized (lock) {
            return lastNode.item;
        }
    }

    /**
     * Gets first node.
     *
     * @return the first node
     */
    public Node<E> getFirstNode() {
        synchronized (lock) {
            return firstNode;
        }
    }

    /**
     * Gets last node.
     *
     * @return the last node
     */
    public Node<E> getLastNode() {
        synchronized (lock) {
            return lastNode;
        }
    }

    /**
     * Sets first node.
     *
     * @param firstNode the first node
     */
    public void setFirstNode(Node<E> firstNode) {
        synchronized (lock) {
            this.firstNode = firstNode;
        }
    }

    /**
     * Sets last node.
     *
     * @param lastNode the last node
     */
    public void setLastNode(Node<E> lastNode) {
        synchronized (lock) {
            this.lastNode = lastNode;
        }
    }


    /**
     * Gets size.
     *
     * @return the size
     */
    public int getSize() {
        synchronized (lock) {
            return size;
        }
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
        synchronized (lock) {
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
}