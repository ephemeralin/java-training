package ru.job4j.producerconsumer.blockingqueue;

import ru.job4j.producerconsumer.DataMessage;

import java.util.LinkedList;
import java.util.List;

/**
 * The type Custom blocking q.
 */
public class CustomBlockingQ implements BlockableQ<DataMessage> {
    /**
     * Queue.
     */
    private List<DataMessage> queue = new LinkedList<>();
    /**
     * Capacity.
     */
    private int capacity;

    /**
     * Instantiates a new Custom blocking q.
     *
     * @param capacity the capacity
     */
    public CustomBlockingQ(int capacity) {
        this.capacity = capacity;
    }

    @Override
    public synchronized void put(DataMessage item) throws InterruptedException {
        while (this.queue.size() == this.capacity) {
            wait();
        }
        if (this.queue.size() == 0) {
            notifyAll();
        }
        this.queue.add(item);
    }

    @Override
    public synchronized DataMessage take() throws InterruptedException {
        while (this.queue.size() == 0) {
            wait();
        }
        if (this.queue.size() == this.capacity) {
            notifyAll();
        }

        return this.queue.remove(0);
    }
}
