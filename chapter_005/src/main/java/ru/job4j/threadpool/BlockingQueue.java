package ru.job4j.threadpool;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Custom blocking q.
 */
public class BlockingQueue {
    /**
     * Queue.
     */
    private List queue = new LinkedList();
    /**
     * Capacity.
     */
    private int capacity;

    /**
     * Instantiates a new Custom blocking q.
     *
     * @param capacity the capacity
     */
    public BlockingQueue(int capacity) {
        this.capacity = capacity;
    }


    /**
     * Put.
     *
     * @param item the item
     * @throws InterruptedException the interrupted exception
     */
    public synchronized void put(Object item) throws InterruptedException {
        while (this.queue.size() == this.capacity) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }

    /**
     * Take object.
     *
     * @return the object
     * @throws InterruptedException the interrupted exception
     */
    public synchronized Object take() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.capacity) {
            notifyAll();
        }

        return this.queue.remove(0);
    }
}
