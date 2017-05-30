package ru.job4j.tracker;

/**
 * Created by ephemeralin on 27.05.17.
 */
public class StartUI {
    /**
     * Add new Item constant.
     */
    private static final int ADD = 0;
    /**
     * Show all items constant.
     */
    private static final int SHOW_ALL = 1;
    /**
     * Edit item constant.
     */
    private static final int EDIT = 2;
    /**
     * Delete item constant.
     */
    private static final int DELETE = 3;
    /**
     * Find item by Id constant.
     */
    private static final int FIND_BY_ID = 4;
    /**
     * Find items by name constant.
     */
    private static final int FIND_BY_NAME = 5;
    /**
     * Exit Program constant.
     */
    private static final int EXIT = 6;
    /**
     * Tracker system constant.
     */
    private static final Tracker TRACKER = new Tracker();

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {

        ConsoleInput input = new ConsoleInput();

        Item currentItem = null;
        int menuItem = -1;

        showMenu();

        escape: while (menuItem != EXIT) {
            menuItem = Integer.parseInt(input.ask());
            switch (menuItem) {
                case ADD:
                    addNewItem();
                    break;

                case SHOW_ALL:
                    showAllItems();
                    break;

                case EDIT:
                    editItem(currentItem);
                    break;

                case DELETE:
                    deleteItem(currentItem);
                    break;

                case FIND_BY_ID:
                    currentItem = findByID();
                    break;

                case FIND_BY_NAME:
                    findByName();
                    break;

                case EXIT:
                    exit();
                    break escape;

                default:
                    wrongOperation();
                    break;
            }
        }
    }


    /**
     * Shows user's menu.
     */
    private static void showMenu() {
        System.out.println("0. Add new Item");
        System.out.println("1. Show all items");
        System.out.println("2. Edit item");
        System.out.println("3. Delete item");
        System.out.println("4. Find item by Id");
        System.out.println("5. Find items by name");
        System.out.println("6. Exit Program");
        System.out.println("Select:");
    }

    /**
     * Add new item.
     */
    private static void addNewItem() {
        ConsoleInput input = new ConsoleInput();
        String name = input.ask("Enter user name: ");
        String description = input.ask("Enter description: ");
        String id = TRACKER.getNextID();
        TRACKER.add(new Item(id, name, description));
        System.out.println("New item has been created with ID: " + id);
    }

    /**
     * Show all items.
     */
    private static void showAllItems() {
        Item[] items = TRACKER.findAll();
        if (items.length == 0) {
            System.out.println("No items.");
        } else {
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }
    }

    /**
     * Edit item.
     *
     * @param item the item
     */
    private static void editItem(Item item) {
        if (item != null) {
            ConsoleInput input = new ConsoleInput();
            String id = item.getId();
            String description = input.ask("Enter new description for item '" + id + "': ");
            Item updatingItem = new Item(item.getId(), item.getName(), description);
            TRACKER.update(updatingItem);
            System.out.println("Item with id '" + id + "' has been updated.");
        } else {
            System.out.println("You have to choose an item for updating!");
        }
    }

    /**
     * Delete item.
     *
     * @param item the item
     */
    public static void deleteItem(Item item) {
        if (item != null) {
            String id = item.getId();
            TRACKER.delete(item);
            System.out.println("Item '" + id + " has been deleted!");
        } else {
            System.out.println("You have to choose an item for deleting!");
        }
    }

    /**
     * Find by id item.
     *
     * @return the item
     */
    public static Item findByID() {
        ConsoleInput input = new ConsoleInput();
        String id = input.ask("Enter ID of the item: ");
        Item item = TRACKER.findByID(id);
        if (item == null) {
            System.out.println("The item with id '" + id + "' hasn't been found! :(");
        }
        return item;
    }

    /**
     * Find by name item [].
     *
     * @return the item []
     */
    public static Item[] findByName() {
        ConsoleInput input = new ConsoleInput();
        String name = input.ask("Enter the name for search: ");
        Item[] items = TRACKER.findByName(name);
        for (Item item : items) {
            System.out.println(item.toString());
        }
        return items;
    }

    /**
     * Exit.
     */
    public static void exit() {
        System.out.println("Bye!");
    }

    /**
     * Wrong operation.
     */
    public static void wrongOperation() {
        System.out.println("Please, choose a valid menu item!");
    }

}
