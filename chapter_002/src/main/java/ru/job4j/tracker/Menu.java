package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * The class for action EditItem.
 */
class EditItem extends BaseAction {

    /**
     * Constructor of the Action class.
     */
    EditItem() {
        super("Add new item", 2);
    }

    /**
     * Implementation of execution of the action EditItem.
     * @param input   the input
     * @param tracker the tracker
     */
    public void execute(Input input, Interacting tracker) {
        String id = input.ask("Enter task ID: ");
        Item item = tracker.findByID(id);
        if (item != null) {
            String description = input.ask("Enter new description for item '" + id + "': ");
            Item updatingItem = new Item(item.getId(), item.getName(), description);
            tracker.update(updatingItem);
            System.out.println("Item with id '" + id + "' has been updated.");
        } else {
            System.out.println("You have to choose an item for updating!");
        }
    }
}


/**
 * Created by ephemeralin on 31.05.17.
 */
public class Menu {

    /**
     * Number of available operations (actions).
     */
    //private static final int NUMBER_OF_OPERATIONS = 7;
    /**
     * Input object.
     */
    private Input input;
    /**
     * Tracker object.
     */
    private Interacting tracker;
    /**
     * List of all available actions.
     */
    //private UserAction[] actions = new UserAction[NUMBER_OF_OPERATIONS];
    private ArrayList<UserAction> actions = new ArrayList<>();

    /**
     * Instantiates a new Menu.
     *
     * @param input   the input
     * @param tracker the tracker
     */
    public Menu(Input input, Interacting tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Create list of actions.
     */
    public void createActions() {
        this.actions.add(this.new AddItem());
        this.actions.add(new Menu.ShowAllItems());
        this.actions.add(new EditItem());
        this.actions.add(this.new DeleteItem());
        this.actions.add(this.new FindItemByID());
        this.actions.add(this.new FindItemsByName());
        this.actions.add(this.new ExitProgram());
    }

    /**
     * Returns range of available actions.
     * @return range
     */
    public int[] getRangeOfActions() {
        int[] range = new int[this.actions.size()];
        for (int i = 0; i < this.actions.size(); i++) {
            range[i] = i;
        }
        return range;
    }

    /**
     * Shows user's menu.
     */
    public void show() {
        System.out.println("----------------");
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
        System.out.println("----------------");
    }

    /**
     * Select.
     * @param key the key
     */
    public void select(int key) {
        this.actions.get(key).execute(this.input, this.tracker);
    }

    /**
     * The class for action AddItem.
     */
    private class AddItem implements UserAction {

        /**
         * Return the key.
         * @return the key of the AddItem action
         */
        public int key() {
            return 0;
        }

        /**
         * Implementation of execution of the action AddItem.
         * @param input   the input
         * @param tracker the tracker
         */
        public void execute(Input input, Interacting tracker) {
            String name = input.ask("Enter user name: ");
            String description = input.ask("Enter description: ");
            String id = tracker.getNextID();
            tracker.add(new Item(id, name, description));
            System.out.println("New item has been created with ID: " + id);
        }

        /**
         * Show info about action.
         * @return string of info
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Add new item");
        }
    }

    /**
     * The class for action ShowAllItems.
     */
    private static class ShowAllItems extends BaseAction {

        /**
         * Constructor of the Action class.
         */
        ShowAllItems() {
            super("Show all items", 1);
        }

        /**
         * Implementation of execution of the action ShowAllItems.
         * @param input   the input
         * @param tracker the tracker
         */
        public void execute(Input input, Interacting tracker) {
            ArrayList<Item> items = tracker.findAll();
            if (items.size() == 0) {
                System.out.println("No items.");
            } else {
                for (Item item : items) {
                    System.out.println(item.toString());
                }
            }
        }
    }

    /**
     * The class for action DeleteItem.
     */
    private class DeleteItem extends BaseAction {

        /**
         * Constructor of the Action class.
         */
        DeleteItem() {
            super("Delete item", 3);
        }

        /**
         * Implementation of execution of the action DeleteItem.
         * @param input   the input
         * @param tracker the tracker
         */
        public void execute(Input input, Interacting tracker) {
            String id = input.ask("Enter task ID: ");
            Item item = tracker.findByID(id);

            if (item != null) {
                tracker.delete(item);
                System.out.println("Item '" + id + " has been deleted!");
            } else {
                System.out.println("You have to choose an item for deleting!");
            }
        }
    }

    /**
     * The class for action FindItemByID.
     */
    private class FindItemByID extends BaseAction {

        /**
         * Constructor of the Action class.
         */
        FindItemByID() {
            super("Find item by ID", 4);
        }

        /**
         * Implementation of execution of the action FindItemByID.
         * @param input   the input
         * @param tracker the tracker
         */
        public void execute(Input input, Interacting tracker) {
            String id = input.ask("Enter task ID: ");
            Item item = tracker.findByID(id);

            if (item == null) {
                System.out.println("The item with id '" + id + "' hasn't been found! :(");
            } else {
                System.out.println("The item has been found: " + item.toString());
            }
        }
    }

    /**
     * The class for action FindItemsByName.
     */
    private class FindItemsByName extends BaseAction {

        /**
         * Constructor of the Action class.
         */
        FindItemsByName() {
            super("Find items by name", 5);
        }

        /**
         * Implementation of execution of the action FindItemsByName.
         * @param input   the input
         * @param tracker the tracker
         */
        public void execute(Input input, Interacting tracker) {
            String name = input.ask("Enter the name for search: ");
            Item[] items = tracker.findByName(name);
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }
    }

    /**
     * The class for action ExitProgram.
     */
    private class ExitProgram extends BaseAction {

        /**
         * Constructor of the Action class.
         */
        ExitProgram() {
            super("Exit programm", 6);
        }

        /**
         * Implementation of execution of the action ExitProgram.
         * @param input   the input
         * @param tracker the tracker
         */
        public void execute(Input input, Interacting tracker) {
            System.out.println("Bye!");
        }

    }
}
