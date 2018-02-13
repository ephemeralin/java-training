package ru.job4j.tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by ephemeralin on 21.05.17.
 */
public class TrackerPostgres implements Interacting, DBConnectable {

    private Connection connection;

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
    public Connection getConnection(Properties properties) {
        if (connection == null) {

            String url = "jdbc:sqlite:/Users/ephemeralin/sqlite/db/" + fileName;

            try {
                connection = DriverManager.getConnection(url);
                System.out.println("Connection established.");

            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        return connection;
    }

    @Override
    public void prepareTables() {

    }
}
