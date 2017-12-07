package ru.job4j.threadpool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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

        ExecutorService e = Executors.newWorkStealingPool();
        int i = 0;
        for (i = 1; i <= Runtime.getRuntime().availableProcessors(); i++) {
            e.execute(new Work(i));
        }
        //run other two works
        e.execute(new Work(i));
        e.execute(new Work(++i));

        e.shutdown();

        Thread.sleep(15000);
    }
}
