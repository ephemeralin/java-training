package ru.job4j.producerconsumer;

import ru.job4j.producerconsumer.blockingqueue.BlockableQ;

/**
 * The Consumer.
 */
public class Consumer implements Runnable {

    /**
     * Data container.
     */
    private BlockableQ<DataMessage> queue;

    /**
     * Instantiates a new Consumer.
     *
     * @param queue the queue
     */
    public Consumer(BlockableQ<DataMessage> queue) {
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