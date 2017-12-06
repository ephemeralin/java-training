package ru.job4j.producerconsumer;

import java.util.concurrent.BlockingQueue;

/**
 * The Producer.
 */
public class Producer implements Runnable {

    /**
     * Data container with produced data.
     */
    private BlockingQueue<DataMessage> dataQueue;

    /**
     * Instantiates a new Producer.
     *
     * @param dataQueue the data queue
     */
    public Producer(BlockingQueue<DataMessage> dataQueue) {
        this.dataQueue = dataQueue;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 5; i++) {
            DataMessage message = new DataMessage("Message " + i);
            try {
                Thread.sleep(10);
                dataQueue.put(message);
                System.out.println("Producer: " + message.getData() + " produced.");
            } catch (Exception e) {
                System.out.println("Exception:" + e);
            }
        }

        DataMessage msg = new DataMessage("exit");
        try {
            dataQueue.put(msg);
            System.out.println("Producer: " + msg.getData());
        } catch (Exception e) {
            System.out.println("Exception:" + e);
        }
    }
}