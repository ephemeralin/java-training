package ru.job4j.tracker;

/**
 * Created by ephemeralin on 27.05.17.
 */
public class StartUI {

    public static void main(String[] args) {
        Item currentItem;

        showMenu();

        Tracker tracker = new Tracker();
        ConsoleInput input = new ConsoleInput();

        int menuItem = -1;
        escape: while (menuItem != 6) {
            menuItem = Integer.parseInt(input.ask());
            switch (menuItem) {
                case 0:
                    String name = input.ask("Input user name: ");
                    String description = input.ask("Input description: ");
                    tracker.add(new Item(tracker.getNextID(), name, description));
                    break;
                case 1:
                    Item[] items = tracker.findAll();
                    for(Item item : items) {
                        System.out.println(item.toString());
                    }
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break escape;
                case - 1:
                    break;
                default:
                    System.out.println("Please, choose a valid menu item!");
                    break;
            }
        }
    }

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
