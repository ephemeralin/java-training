package ru.job4j.producerconsumer;

import java.util.concurrent.BlockingQueue;

/**
 * The Consumer.
 */
public class Consumer implements Runnable {

    /**
     * Data container.
     */
    private BlockingQueue<DataMessage> queue;

    /**
     * Instantiates a new Consumer.
     *
     * @param queue the queue
     */
    public Consumer(BlockingQueue<DataMessage> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        try {
            DataMessage message;

            while ((message = queue.take()).getData() != "exit") {
                Thread.sleep(10);
                System.out.println("Consumer: " + message.getData() + " consumed.");
            }
            System.out.println("Consumer: exit");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}