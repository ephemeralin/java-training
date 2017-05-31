package ru.job4j.tracker;

/**
 * Created by ephemeralin on 31.05.17.
 */
public abstract class Menu {
    /**
     * Add new Item constant.
     */
    public static final int ADD = 0;
    /**
     * Show all items constant.
     */
    public static final int SHOW_ALL = 1;
    /**
     * Edit item constant.
     */
    public static final int EDIT = 2;
    /**
     * Delete item constant.
     */
    public static final int DELETE = 3;
    /**
     * Find item by Id constant.
     */
    public static final int FIND_BY_ID = 4;
    /**
     * Find items by name constant.
     */
    public static final int FIND_BY_NAME = 5;
    /**
     * Exit Program constant.
     */
    public static final int EXIT = 6;

    /**
     * Shows user's menu.
     */
    public static void show() {
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        System.out.println("Select:");
    }
}
