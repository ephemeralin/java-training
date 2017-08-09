package ru.job4j.tracker;

/**
 * Created by ephemeralin on 09.06.17.
 */
public interface UserAction {
    /**
     * Key of the action.
     *
     * @return the int
     */
    int key();

    /**
     * Execute the action.
     *
     * @param input   the input
     * @param tracker the tracker
     */
    void execute(Input input, Tracker tracker);

    /**
     * Info of the action.
     *
     * @return the string
     */
    String info();
}
