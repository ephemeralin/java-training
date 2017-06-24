package ru.job4j.tracker;

/**
 * Created by ephemeralin on 31.05.17.
 */
public class StubInput implements Input {
    /**
     * Array of Answers.
     */
    private String[] answers;

    /**
     * Position.
     */
    private int position = 0;

    /**
     * Instantiates a new Stub input.
     *
     * @param answers the answers
     */
    public StubInput(String[] answers) {
        this.answers = answers;
    }

    /**
     * Ask string.
     *
     * @param question the string of question
     * @return the position
     */
    public String ask(String question) {
        return answers[position++];
    }

    /**
     * Ask number of action.
     * @param question the string of question
     * @param range the range of available actions
     * @return number of action
     */
    public int ask(String question, int[] range) {
        //return new UnsupportedOperationException("Unsupported operation!");
        return -1;
    }
}
