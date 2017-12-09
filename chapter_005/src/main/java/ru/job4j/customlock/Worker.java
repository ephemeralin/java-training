package ru.job4j.customlock;

/**
 * The type Worker.
 */
public class Worker implements Runnable {

    /**
     * Lock.
     */
    private Lock lock = new Lock();

    /**
     * Data.
     */
    private int count = 0;

    @Override
    public void run() {
        try {
            work();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Work int.
     *
     * @return the int
     * @throws InterruptedException the interrupted exception
     */
    public int work() throws InterruptedException {
        lock.lock();

        int newCount = count++;
        Thread.sleep(1000);

        lock.unlock();

        return newCount;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new Worker());
        thread.start();
    }

}