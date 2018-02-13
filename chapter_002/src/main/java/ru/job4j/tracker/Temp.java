package ru.job4j.tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The type Util.
 */
public class Temp {

    /**
     * The constant PATH.
     */
    public static final String PATH = "chapter_006/src/main/java/ru/job4j/sqlxmlxsltjdbc/resources/";
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Gets connection.
     *
     * @param fileName the file name
     * @return the connection
     */
    public Connection getConnection(String fileName) {

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

    /**
     * Prepare table boolean.
     *
     * @param connection the connection
     * @return the boolean
     */
    public static boolean prepareTable(Connection connection) {
        Statement stmt = null;
        String sqlCreateTable = "CREATE TABLE IF NOT EXISTS data (id INTEGER PRIMARY KEY);";
        String sqlDropTable = "DELETE FROM data;";

        boolean isPrepared = false;

        try {
            stmt = connection.createStatement();
            stmt.execute(sqlCreateTable);
            stmt.execute(sqlDropTable);
            isPrepared = true;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isPrepared;
    }
}
