package ru.job4j.tracker;

/**
 * Created by ephemeralin on 30.06.17.
 */
public abstract class BaseAction implements UserAction {

    /**
     * The name of the Action.
     */
    private String name;

    /**
     * The key of the Action.
     */
    private int key;

    /**
     * Return the key.
     * @return the key of the EditItem action
     */
    @Override
    public int key() {
        return key;
    }

    /**
     * Main method for execution of the Action.
     * @param input   the input
     * @param tracker the tracker
     */
    @Override
    public abstract void execute(Input input, Tracker tracker);

    /**
     * Show info about action.
     * @return string of info
     */
    @Override
    public String info() {
        return String.format("%s. %s", this.key, this.name);
    }

    /**
     * Instantiates a new Base action.
     *
     * @param name the name
     * @param key  the key
     */
    public BaseAction(String name, int key) {
        this.name = name;
        this.key = key;
    }
}
