package ru.job4j.tracker;

import java.util.ArrayList;

/**
 * The interface Interacting.
 */
public interface Interacting {
    /**
     * Add item.
     *
     * @param item the item
     * @return the item
     */
    Item add(Item item);

    /**
     * Update.
     *
     * @param item the item
     */
    void update(Item item);

    /**
     * Delete.
     *
     * @param item the item
     */
    void delete(Item item);

    /**
     * Find all array list.
     *
     * @return the array list
     */
    ArrayList<Item> findAll();

    /**
     * Find by name item [ ].
     *
     * @param name the name
     * @return the item [ ]
     */
    Item[] findByName(String name);

    /**
     * Find by id item.
     *
     * @param id the id
     * @return the item
     */
    Item findByID(String id);

    /**
     * Gets next id.
     *
     * @return the next id
     */
    String getNextID();
}
