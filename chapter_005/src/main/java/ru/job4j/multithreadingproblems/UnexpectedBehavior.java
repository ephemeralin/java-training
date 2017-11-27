package ru.job4j.multithreadingproblems;

/**
 * The type Unexpected behavior.
 */
public class UnexpectedBehavior {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        Counter counter = new Counter();
        for (int i = 0; i < 200; i++) {
            CounterThread ct = new CounterThread(counter);
            ct.start();
        }
        Thread.sleep(1000);

        System.out.println("Counter: " + counter.getCounter());
    }
}

/**
 * The type Counter.
 */
class Counter {
    /**
     * Counter variable which will increase.
     */
    private long counter = 0L;

    /**
     * Increase counter.
     */
    public void increaseCounter() {
        counter++;
    }

    /**
     * Gets counter.
     *
     * @return the counter
     */
    public long getCounter() {
        return counter;
    }
}

/**
 * The type Counter thread.
 */
class CounterThread extends Thread {
    /**
     * Counter object which will increase.
     */
    private Counter counter;

    /**
     * Instantiates a new Counter thread.
     *
     * @param counter the counter
     */
    CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 1000; i++) {
            counter.increaseCounter();
        }
    }
}
