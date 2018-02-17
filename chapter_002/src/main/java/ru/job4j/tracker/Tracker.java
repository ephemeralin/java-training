package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * Created by ephemeralin on 21.05.17.
 */
public class Tracker implements Interacting {
    /**
     * Array of Items.
     */
    private ArrayList<Item> items = new ArrayList<>();

    /**
     * Index of the last Item.
     */
    private int lastItemIndex = 0;

    /**
     * Add item.
     *
     * @param item the item
     * @return the item
     */
    public Item add(Item item) {
        this.items.add(item);
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
        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).equals(updatedItem)) {
                this.items.set(i, item);
            }
        }
    }

    /**
     * Delete.
     *
     * @param item the item
     */
    public void delete(Item item) {
        items.remove(item);
        items.trimToSize();
    }

    /**
     * Find all items.
     *
     * @return array of all items
     */
    public ArrayList<Item> findAll() {
        ArrayList<Item> allItems = new ArrayList<>();
        for (Item i: items) {
            allItems.add(i);
        }
        return allItems;
    }

    /**
     * Find by name item [].
     *
     * @param name the name
     * @return the item []
     */
    public ArrayList<Item> findByName(String name) {
        ArrayList<Item> foundItems = new ArrayList<>();
        for (Item i: items) {
            if (i != null && i.getName().equals(name)) {
                foundItems.add(i);
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
            if (i != null && i.getId().equals(id)) {
                foundItem = i;
                break;
            }
        }
        return foundItem;
    }

    /**
     * Gets next id.
     *
     * @return the next id
     */
    public String getNextID() {
        return "task" + lastItemIndex;
    }

    @Override
    public void closeConnection() {
        //mock
    }

}
