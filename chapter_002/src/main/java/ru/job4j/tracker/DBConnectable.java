package ru.job4j.tracker;

import java.sql.SQLException;

/**
 * The interface database connectable.
 */
public interface DBConnectable {
    /**
     * Init databse connection.
     *
     * @throws SQLException the sql exception
     */
    void initConnection() throws SQLException;

    /**
     * Prepare tracker for doing work.
     */
    void prepare();
}
