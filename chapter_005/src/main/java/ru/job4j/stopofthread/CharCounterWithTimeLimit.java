package ru.job4j.stopofthread;

/**
 * The type Char counter with time limit.
 */
public class CharCounterWithTimeLimit {
    /**
     * Text string for analyzing.
     */
    private String text;
    /**
     * Time limit in milliseconds.
     */
    private long timeLimitMillis;

    /**
     * Instantiates a new Char counter with time limit.
     *
     * @param text            the text
     * @param timeLimitMillis the time limit millis
     */
    public CharCounterWithTimeLimit(String text, long timeLimitMillis) {
        this.text = text;
        this.timeLimitMillis = timeLimitMillis;
    }

    /**
     * The type Time.
     */
    public class Time implements Runnable {

        @Override
        public void run() {
            try {
                Thread.currentThread().sleep(timeLimitMillis);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            Thread.currentThread().interrupt();
        }
    }

    /**
     * The type Symbol counter.
     */
    public class SymbolCounter implements Runnable {

        @Override
        public void run() {
            long startTime = System.nanoTime();

            Thread timerThread = new Thread(new Time());
            timerThread.start();

            char[] chars = text.toCharArray();
            int count = 0;
            boolean isInterrupted = false;

            for (int i = 0; i < chars.length; i++) {
                if (chars[i] != ' ') {
                    count++;
                }
                if (timerThread.isInterrupted()) {
                    System.out.println("Process was interrupted!");
                    isInterrupted = true;
                    break;
                }
            }
            long usedTime = System.nanoTime() - startTime;

            if (!isInterrupted) {
                System.out.println("Number of symbols: " + count);
                System.out.println("Limit time (ms): " + (double) timeLimitMillis);
                System.out.println("Used time (ms): " + usedTime / 1000000.0);
            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        String text = SampleBigStringGenerator.get();
        CharCounterWithTimeLimit counter = new CharCounterWithTimeLimit(text, 5);

        Thread counterThread = new Thread(counter.new SymbolCounter());
        counterThread.start();
    }
}
