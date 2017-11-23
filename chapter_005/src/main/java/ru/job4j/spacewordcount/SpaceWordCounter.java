package ru.job4j.spacewordcount;

/**
 * The type Space word counter.
 */
public class SpaceWordCounter {

    /**
     * Text string.
     */
    private String text;

    /**
     * Instantiates a new Space word counter.
     *
     * @param text the text
     */
    public SpaceWordCounter(String text) {
        this.text = text;
    }

    /**
     * The type Space counter.
     */
    public class SpaceCounter implements Runnable {

        @Override
        public void run() {
            System.out.println(String.format("Number of spaces is %d", countSpaces()));
        }
    }

    /**
     * The type Word counter.
     */
    public class WordCounter implements Runnable {

        @Override
        public void run() {
            System.out.println(String.format("Number of words is %d", countWords()));
        }
    }


    /**
     * Count spaces int.
     *
     * @return the int
     */
    public int countSpaces() {
        int count = 0;
        char[] chars = this.text.toCharArray();
        for (Character c : chars) {
            if (c == ' ') {
                count++;
            }
        }
        return count;
    }

    /**
     * Count words int.
     *
     * @return the int
     */
    public int countWords() {
        int count = 0;
        char[] chars = this.text.toCharArray();
        boolean newWord = false;
        for (Character c : chars) {
            if (c != ' ') {
                if (!newWord) {
                    count++;
                }
                newWord = true;
            } else {
                if (newWord) {
                    newWord = false;
                }
            }
        }

        return count;
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SpaceWordCounter counter = new SpaceWordCounter("There is just a sentence");

        Thread threadOne = new Thread(counter.new SpaceCounter());
        threadOne.start();

        Thread threadTwo = new Thread(counter.new WordCounter());
        threadTwo.start();

    }
}
