package ru.job4j.threadpool;

/**
 * The type Work.
 */
public class Work implements Runnable {
    /**
     * Number of the Work.
     */
    private int number;

    /**
     * Instantiates a new Work.
     *
     * @param number the number
     */
    public Work(int number) {
        this.number = number;
    }

    @Override
    public void run() {
//        try {
            System.out.println("Start work " + number);
//            Thread.sleep(5000);
            System.out.println("    Finish work " + number);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }
}
