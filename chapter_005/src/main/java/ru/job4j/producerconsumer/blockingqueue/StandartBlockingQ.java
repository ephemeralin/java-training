package ru.job4j.producerconsumer.blockingqueue;

import ru.job4j.producerconsumer.DataMessage;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * The type Standart blocking q.
 */
public class StandartBlockingQ implements BlockableQ<DataMessage> {
    /**
     * Queue.
     */
    private BlockingQueue<DataMessage> queue;

    /**
     * Instantiates a new Standart blocking q.
     *
     * @param capacity the capacity
     */
    public StandartBlockingQ(int capacity) {
        this.queue = new ArrayBlockingQueue<>(capacity);
    }

    @Override
    public void put(DataMessage o) throws InterruptedException {
        this.queue.put(o);
    }

    @Override
    public DataMessage take() throws InterruptedException {
        return this.queue.take();
    }
}
