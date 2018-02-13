package ru.job4j.sqlxmlxsltjdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public final class DatabaseConnection {
    private static DatabaseConnection instance = null;
    private Connection connection;
    private String dbName;

    public DatabaseConnection(String dbName) {
        this.dbName = dbName;

    }

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

    public boolean prepareTable() {
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS data (id INTEGER PRIMARY KEY);";
        String sqlDropTable = "DELETE FROM data;";
        boolean isPrepared = false;
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sqlCreateTable);
            stmt.execute(sqlDropTable);
            isPrepared = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return isPrepared;
    }
}

