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

        Item currentItem = null;
        int menuItem = -1;

        Menu.show();

        escape: while (menuItem != Menu.EXIT) {
            menuItem = Integer.parseInt(input.ask());
            if (menuItem == Menu.ADD) {
                addNewItem(input);

            } else if (menuItem == Menu.SHOW_ALL) {
                showAllItems();

            } else if (menuItem == Menu.EDIT) {
                editItem(input, currentItem);

            } else if (menuItem == Menu.DELETE) {
                deleteItem(currentItem);

            } else if (menuItem == Menu.FIND_BY_ID) {
                currentItem = findByID(input);

            } else if (menuItem == Menu.FIND_BY_NAME) {
                findByName(input);

            } else if (menuItem == Menu.EXIT) {
                exit();
                break escape;
            } else {
                wrongOperation();

            }
        }
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        Input input = new ConsoleInput();
        new StartUI(tracker, input).init();

    }

    /**
     * Add new item.
     * @param input the input
     */
    private void addNewItem(Input input) {
        System.out.println("Enter user name: ");
        String name = input.ask();
        System.out.println("Enter description: ");
        String description = input.ask();
        String id = tracker.getNextID();
        tracker.add(new Item(id, name, description));
        System.out.println("New item has been created with ID: " + id);
    }

    /**
     * Show all items.
     */
    private void showAllItems() {
        Item[] items = tracker.findAll();
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
     * @param input the input
     * @param item the item
     */
    private void editItem(Input input, Item item) {
        if (item != null) {
            String id = item.getId();
            System.out.println("Enter new description for item '" + id + "': ");
            String description = input.ask();
            Item updatingItem = new Item(item.getId(), item.getName(), description);
            tracker.update(updatingItem);
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
    public void deleteItem(Item item) {
        if (item != null) {
            String id = item.getId();
            tracker.delete(item);
            System.out.println("Item '" + id + " has been deleted!");
        } else {
            System.out.println("You have to choose an item for deleting!");
        }
    }

    /**
     * Find by id item.
     *
     * @param input the input
     * @return the item
     */
    public Item findByID(Input input) {
        System.out.println("Enter ID of the item: ");
        String id = input.ask();
        Item item = tracker.findByID(id);
        if (item == null) {
            System.out.println("The item with id '" + id + "' hasn't been found! :(");
        }
        return item;
    }

    /**
     * Find by name item [].
     *
     * @param input the input
     * @return the item []
     */
    public Item[] findByName(Input input) {
        System.out.println("Enter the name for search: ");
        String name = input.ask();
        Item[] items = tracker.findByName(name);
        for (Item item : items) {
            System.out.println(item.toString());
        }
        return items;
    }

    /**
     * Exit.
     */
    public void exit() {
        System.out.println("Bye!");
    }

    /**
     * Wrong operation.
     */
    public void wrongOperation() {
        System.out.println("Please, choose a valid menu item!");
    }

}
