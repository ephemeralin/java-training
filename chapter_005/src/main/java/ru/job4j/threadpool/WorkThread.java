package ru.job4j.threadpool;


/**
 * The type Work thread.
 */
public class WorkThread extends Thread {

    /**
     * Task queue.
     */
    private BlockingQueue taskQueue = null;
    /**
     * Is stopped.
     */
    private boolean isStopped = false;

    /**
     * Instantiates a new Work thread.
     *
     * @param queue the queue
     */
    public WorkThread(BlockingQueue queue) {
        taskQueue = queue;
    }

    /**
     * Run thread.
     */
    public void run() {
        while (!isStopped()) {
            try {
                Runnable runnable = (Runnable) taskQueue.take();
                runnable.run();
            } catch (Exception e) {
            }
        }
    }

    /**
     * Do stop.
     */
    public synchronized void doStop() {
        isStopped = true;
        this.interrupt();
    }

    /**
     * Is stopped boolean.
     *
     * @return the boolean
     */
    public synchronized boolean isStopped() {
        return isStopped;
    }
}
