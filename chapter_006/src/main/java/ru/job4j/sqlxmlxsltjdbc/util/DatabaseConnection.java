package ru.job4j.sqlxmlxsltjdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The type Database connection.
 */
public final class DatabaseConnection {
    /**
     * Connection.
     */
    private Connection connection;
    /**
     * DB name.
     */
    private String dbName;

    /**
     * Instantiates a new Database connection.
     *
     * @param dbName the db name
     */
    public DatabaseConnection(String dbName) {
        this.dbName = dbName;
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        String url = "jdbc:sqlite:/Users/ephemeralin/sqlite/db/" + this.dbName;
        try {
            connection = DriverManager.getConnection(url);
            System.out.println("Connection established.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return connection;
    }

    /**
     * Prepare database structure boolean.
     *
     * @param sql the sql
     * @return the boolean
     */
    public boolean prepareDatabaseStructure(String sql) {
        boolean isPrepared = false;
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            isPrepared = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isPrepared;
    }
}
