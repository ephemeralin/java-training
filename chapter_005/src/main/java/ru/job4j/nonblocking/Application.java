package ru.job4j.nonblocking;

import ru.job4j.nonblocking.cache.NonBlockingCache;
import ru.job4j.nonblocking.cache.Task;

/**
 * The type Application.
 */
public class Application {
    /**
     * Non blocking cache.
     */
    private NonBlockingCache cache;

    /**
     * Instantiates a new Application.
     */
    private Application() {
        this.cache = new NonBlockingCache();
    }

    /**
     * Start.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void start() throws InterruptedException {
        Task task = new Task("task", "data1");
        cache.add(task);

        Thread thread1 = new Thread(new ChangeTask(1, task, "task_A"));
        Thread thread2 = new Thread(new ChangeTask(2, task, "task_B"));
        Thread thread3 = new Thread(new ChangeTask(3, task, "task_C"));

        thread1.run();
        thread2.run();
        thread3.run();

        Thread.sleep(1000);
    }

    /**
     * The type Change task.
     */
    public class ChangeTask implements Runnable {
        /**
         * Number of thread.
         */
        private int numberOfThread;
        /**
         * Task.
         */
        private Task task;
        /**
         * New name.
         */
        private String newName;

        /**
         * Instantiates a new Change task.
         *
         * @param numberOfThread the number of thread
         * @param task           the task
         * @param newName        the new name
         */
        public ChangeTask(int numberOfThread, Task task, String newName) {
            this.numberOfThread = numberOfThread;
            this.task = task;
            this.newName = newName;
        }

        @Override
        public void run() {
            System.out.println(String.format("\n...thread %s", numberOfThread));

            Task taskUpdate = cache.get(task.getId());

            taskUpdate.setName(newName);

            try {
                cache.update(taskUpdate);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {

        Application app = new Application();
        app.start();

    }

}
