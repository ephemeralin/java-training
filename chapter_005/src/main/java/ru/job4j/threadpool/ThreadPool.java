package ru.job4j.threadpool;


import java.util.ArrayList;
import java.util.List;

/**
 * The type Thread pool.
 */
public class ThreadPool {

    /**
     * Task queue.
     */
    private BlockingQueue taskQueue = null;
    /**
     * Threads list.
     */
    private List<WorkThread> threads = new ArrayList<WorkThread>();
    /**
     * Is stopped.
     */
    private boolean isStopped = false;

    /**
     * Instantiates a new Thread pool.
     *
     * @param noOfThreads  the no of threads
     * @param maxNoOfTasks the max no of tasks
     */
    public ThreadPool(int noOfThreads, int maxNoOfTasks) {
        taskQueue = new BlockingQueue(maxNoOfTasks);

        for (int i = 0; i < noOfThreads; i++) {
            threads.add(new WorkThread(taskQueue));
        }
        for (WorkThread thread : threads) {
            thread.start();
        }
    }

    /**
     * Execute.
     *
     * @param task the task
     * @throws Exception the exception
     */
    public synchronized void  execute(Runnable task) throws Exception {
        if (this.isStopped) {
            throw new IllegalStateException("ThreadPool is stopped");
        }

        this.taskQueue.put(task);
    }

    /**
     * Stop.
     */
    public synchronized void stop() {
        this.isStopped = true;
        for (WorkThread thread : threads) {
            thread.doStop();
        }
    }
}
