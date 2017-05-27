package ru.job4j.tracker;

/**
 * Created by ephemeralin on 21.05.17.
 */
public class Tracker {
    /**
     * Array of Items.
     */
    private Item[] items;

    /**
     * Index of the last Item.
     */
    private int lastItemIndex = 0;

    /**
     * Instantiates a new Tracker.
     */
    public Tracker() {
        this.items = new Item[100];
    }

    /**
     * Add item.
     *
     * @param item the item
     * @return the item
     */
    public Item add(Item item) {
        this.items[lastItemIndex] = item;
        lastItemIndex++;
        return item;
    }

    /**
     * Update.
     *
     * @param item the item
     */
    public void update(Item item) {
        Item updatedItem = findByID(item.getId());
        for (int i = 0; i < lastItemIndex; i++) {
            if (this.items[i].equals(updatedItem)) {
                this.items[i] = item;
            }
        }
    }

    /**
     * Delete.
     *
     * @param item the item
     */
    public void delete(Item item) {
        for (int i = 0; i <= lastItemIndex; i++) {
            if (this.items[i] == item) {
                for (int j = i; j <= lastItemIndex; j++) {
                    int nextJ = j + 1;
                    if (nextJ < this.items.length) {
                        this.items[j] = items[nextJ];
                    } else {
                        this.items[j] = null;
                    }
                }
                break;
            }
        }


    }

    /**
     * Find all item [ ].
     *
     * @return the item [ ]
     */
    public Item[] findAll() {
        int numberOfItems = 0;
        for (Item i: items) {
            if (i != null) {
                numberOfItems++;
            }
        }

        Item[] allItems = new Item[numberOfItems];
        int index = 0;
        for (Item i: items) {
            if (i != null) {
                allItems[index] = i;
                index++;
            }
        }
        return allItems;
    }

    /**
     * Find by name item [ ].
     *
     * @param name the name
     * @return the item [ ]
     */
    public Item[] findByName(String name) {
        int numberOfFoundItems = 0;
        for (Item i: items) {
            if (i != null && i.getName().equals(name)) {
                numberOfFoundItems++;
            }
        }

        Item[] foundItems = new Item[numberOfFoundItems];
        int index = 0;
        for (Item i: items) {
            if (i != null && i.getName().equals(name)) {
                foundItems[index] = i;
                index++;
            }
        }
        return foundItems;
    }

    /**
     * Find by id item.
     *
     * @param id the id
     * @return the item
     */
    public Item findByID(String id) {
        Item foundItem = null;
        for (Item i: items) {
            if (i.getId().equals(id)) {
                foundItem = i;
                break;
            }
        }
        return foundItem;
    }


}
