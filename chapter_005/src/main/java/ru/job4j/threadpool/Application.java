package ru.job4j.threadpool;

/**
 * The Application.
 */
public class Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the interrupted exception
     */
    public static void main(String[] args) throws Exception {

        //standard pool

//        ExecutorService e = Executors.newWorkStealingPool();
//        int i = 0;
//        for (i = 1; i <= Runtime.getRuntime().availableProcessors(); i++) {
//            e.execute(new Work(i));
//        }
//        //run other two works
//        e.execute(new Work(i));
//        e.execute(new Work(++i));
//
//        e.shutdown();
//
//        Thread.sleep(15000);


        //custom thread pool implementation
        int numberOfCores = Runtime.getRuntime().availableProcessors();
        int numberOfWorks = numberOfCores + 2;

        ThreadPool pool = new ThreadPool(numberOfWorks, numberOfCores);
        int i = 0;
        for (i = 1; i <= numberOfWorks; i++) {
            pool.execute(new Work(i));
        }
        pool.stop();
        Thread.sleep(2000);
    }
}
