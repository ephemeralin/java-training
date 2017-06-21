package ru.job4j.tracker;


/**
 * The class for action EditItem.
 */
class EditItem implements UserAction {

    /**
     * Return the key.
     * @return the key of the EditItem action
     */
    public int key() {
        return 2;
    }

    /**
     * Implementation of execution of the action EditItem.
     * @param input   the input
     * @param tracker the tracker
     */
    public void execute(Input input, Tracker tracker) {
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

    /**
     * Show info about action.
     * @return string of info
     */
    public String info() {
        return String.format("%s. %s", this.key(), "Add new item");
    }
}


/**
 * Created by ephemeralin on 31.05.17.
 */
public class Menu {
    /**
     * Input object.
     */
    private Input input;
    /**
     * Tracker object.
     */
    private Tracker tracker;
    /**
     * List of all available actions.
     */
    private UserAction[] actions = new UserAction[7];

    /**
     * Instantiates a new Menu.
     *
     * @param input   the input
     * @param tracker the tracker
     */
    public Menu(Input input, Tracker tracker) {
        this.input = input;
        this.tracker = tracker;
    }

    /**
     * Create list of actions.
     */
    public void createActions() {
        this.actions[0] = this.new AddItem();
        this.actions[1] = new Menu.ShowAllItems();
        this.actions[2] = new EditItem();
        this.actions[3] = this.new DeleteItem();
        this.actions[4] = this.new FindItemByID();
        this.actions[5] = this.new FindItemsByName();
        this.actions[6] = this.new ExitProgram();
    }

    /**
     * Shows user's menu.
     */
    public void show() {
        for (UserAction action : this.actions) {
            if (action != null) {
                System.out.println(action.info());
            }
        }
    }

    /**
     * Select.
     * @param key the key
     */
    public void select(int key) {
        this.actions[key].execute(this.input, this.tracker);
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
        public void execute(Input input, Tracker tracker) {
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
    private static class ShowAllItems implements UserAction {

        /**
         * Return the key.
         * @return the key of ShowAllItems action
         */
        public int key() {
            return 1;
        }

        /**
         * Implementation of execution of the action ShowAllItems.
         * @param input   the input
         * @param tracker the tracker
         */
        public void execute(Input input, Tracker tracker) {
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
         * Show info about action.
         * @return string of info
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Show all items");
        }
    }

    /**
     * The class for action DeleteItem.
     */
    private class DeleteItem implements UserAction {

        /**
         * Return the key.
         * @return the key of the DeleteItem action
         */
        public int key() {
            return 3;
        }

        /**
         * Implementation of execution of the action DeleteItem.
         * @param input   the input
         * @param tracker the tracker
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter task ID: ");
            Item item = tracker.findByID(id);

            if (item != null) {
                tracker.delete(item);
                System.out.println("Item '" + id + " has been deleted!");
            } else {
                System.out.println("You have to choose an item for deleting!");
            }
        }

        /**
         * Show info about action.
         * @return string of info
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Delete item");
        }
    }

    /**
     * The class for action FindItemByID.
     */
    private class FindItemByID implements UserAction {

        /**
         * Return the key.
         * @return the key of the FindItemByID action
         */
        public int key() {
            return 4;
        }

        /**
         * Implementation of execution of the action FindItemByID.
         * @param input   the input
         * @param tracker the tracker
         */
        public void execute(Input input, Tracker tracker) {
            String id = input.ask("Enter task ID: ");
            Item item = tracker.findByID(id);

            if (item == null) {
                System.out.println("The item with id '" + id + "' hasn't been found! :(");
            } else {
                System.out.println("The item has been found: " + item.toString());
            }
        }

        /**
         * Show info about action.
         * @return string of info
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find item by ID");
        }
    }

    /**
     * The class for action FindItemsByName.
     */
    private class FindItemsByName implements UserAction {

        /**
         * Return the key.
         * @return the key of the FindItemsByName action
         */
        public int key() {
            return 5;
        }

        /**
         * Implementation of execution of the action FindItemsByName.
         * @param input   the input
         * @param tracker the tracker
         */
        public void execute(Input input, Tracker tracker) {
            String name = input.ask("Enter the name for search: ");
            Item[] items = tracker.findByName(name);
            for (Item item : items) {
                System.out.println(item.toString());
            }
        }

        /**
         * Show info about action.
         * @return string of info
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Find items by name");
        }
    }

    /**
     * The class for action ExitProgram.
     */
    private class ExitProgram implements UserAction {

        /**
         * Return the key.
         * @return the key of the ExitProgram action
         */
        public int key() {
            return 6;
        }

        /**
         * Implementation of execution of the action ExitProgram.
         * @param input   the input
         * @param tracker the tracker
         */
        public void execute(Input input, Tracker tracker) {
            System.out.println("Bye!");
        }

        /**
         * Show info about action.
         * @return string of info
         */
        public String info() {
            return String.format("%s. %s", this.key(), "Exit program");
        }
    }

}
