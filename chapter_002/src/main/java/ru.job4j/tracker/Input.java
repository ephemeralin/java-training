package ru.job4j.tracker;

/**
 * Created by ephemeralin on 27.05.17.
 */
public interface Input {
    /**
     * Ask string.
     *
     * @param question the string of question
     * @return the string
     */
    String ask(String question);

    /**
     * Ask int.
     *
     * @param question the string of question
     * @param range the range of available actions
     * @return the number of action from range
     */
    int ask(String question, int[] range);

}
