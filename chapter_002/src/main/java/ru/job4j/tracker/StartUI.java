package ru.job4j.tracker;

import java.sql.SQLException;

/**
 * Created by ephemeralin on 27.05.17.
 */
public class StartUI {
    /**
     * Tracker system.
     */
    private Interacting tracker;
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
    public StartUI(Interacting tracker, Input input) {
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
        //keeping data in memory
        //Tracker tracker = new Tracker();

        //keeping data in postgresql
        String resourcesPath = "chapter_002/resources/tracker/";
        TrackerPostgres tracker = new TrackerPostgres(resourcesPath);
        try {
            tracker.initConnection();
            tracker.prepare();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //starting UI
        Input input = new ValidatedInput();
        new StartUI(tracker, input).init();
    }
}
