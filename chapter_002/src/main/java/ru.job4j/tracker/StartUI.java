package ru.job4j.tracker;

/**
 * Created by ephemeralin on 27.05.17.
 */
public class StartUI {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Item currentItem = null;

        showMenu();

        Tracker tracker = new Tracker();
        ConsoleInput input = new ConsoleInput();

        String id, name, description;
        Item[] items;

        int menuItem = -1;
        escape: while (menuItem != 6) {
            menuItem = Integer.parseInt(input.ask());
            switch (menuItem) {
                case 0:
                    name = input.ask("Enter user name: ");
                    description = input.ask("Enter description: ");
                    id = tracker.getNextID();
                    tracker.add(new Item(id, name, description));
                    System.out.println("New item has been created with ID: " + id);
                    break;
                case 1:
                    items = tracker.findAll();
                    if (items.length == 0) {
                        System.out.println("No items.");
                    } else {
                        for (Item item : items) {
                            System.out.println(item.toString());
                        }
                    }
                    break;
                case 2:
                    if (currentItem != null) {
                        id = currentItem.getId();
                        description = input.ask("Enter new description for item '" + id + "': ");
                        Item updatingItem = new Item(currentItem.getId(), currentItem.getName(), description);
                        tracker.update(updatingItem);
                        System.out.println("Item with id '" + id + "' has been updated.");
                    } else {
                        System.out.println("You have to choose an item for updating!");
                    }
                    break;
                case 3:
                    if (currentItem != null) {
                        id = currentItem.getId();
                        tracker.delete(currentItem);
                        System.out.println("Item '" + id + " has been deleted!");
                    } else {
                        System.out.println("You have to choose an item for deleting!");
                    }
                    break;
                case 4:
                    id = input.ask("Enter ID of the item: ");
                    currentItem = tracker.findByID(id);
                    if (currentItem == null) {
                        System.out.println("The item with id '" + id + "' hasn't been found! :(");
                    }
                    break;
                case 5:
                    name = input.ask("Enter the name for search: ");
                    items = tracker.findByName(name);
                    for (Item item : items) {
                        System.out.println(item.toString());
                    }
                    break;
                case 6:
                    System.out.println("Bye!");
                    break escape;
                case -1:
                    break;
                default:
                    System.out.println("Please, choose a valid menu item!");
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
}
