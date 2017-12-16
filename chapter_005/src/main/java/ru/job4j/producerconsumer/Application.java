package ru.job4j.producerconsumer;

import ru.job4j.producerconsumer.blockingqueue.BlockableQ;
import ru.job4j.producerconsumer.blockingqueue.CustomBlockingQ;
import ru.job4j.producerconsumer.blockingqueue.StandartBlockingQ;


/**
 * The Application.
 */
public class Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {

        BlockableQ<DataMessage> queue = new StandartBlockingQ(10);
        Producer producer = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        System.out.println("\nProducer / Consumer Test Started (STANDARD blocking queue).");

        Thread.sleep(2000);

        queue = new CustomBlockingQ(10);
        producer = new Producer(queue);
        consumer = new Consumer(queue);

        new Thread(producer).start();
        new Thread(consumer).start();

        System.out.println("\nProducer / Consumer Test Started (CUSTOM blocking queue).");
    }
}
