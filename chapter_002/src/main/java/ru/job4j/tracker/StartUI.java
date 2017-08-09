package ru.job4j.tracker;

/**
 * Created by ephemeralin on 27.05.17.
 */
public class StartUI {
    /**
     * Tracker system.
     */
    private Tracker tracker;
    /**
     * Input system.
     */
    private Input input;

    /**
     * Instantiates a new Start ui.
     *
     * @param tracker the tracker
     * @param input   the input
     */
    public StartUI(Tracker tracker, Input input) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Init.
     */
    public void init() {

        Menu menu = new Menu(this.input, this.tracker);
        menu.createActions();
        int key;
        do {
            menu.show();
            key = input.ask("Select: ", menu.getRangeOfActions());
            menu.select(key);
        } while (key != 6);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ValidatedInput();
        new StartUI(tracker, input).init();
    }

}
