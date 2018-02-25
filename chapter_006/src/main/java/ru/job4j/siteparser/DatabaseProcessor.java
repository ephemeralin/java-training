package ru.job4j.siteparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;
import java.util.HashSet;

/**
 * The type Database processor.
 */
public class DatabaseProcessor {

    /**
     * Connection.
     */
    private Connection connection;
    /**
     * Properties storage.
     */
    private PropertiesStorage propertiesStorage;
    /**
     * Logger.
     */
    private final Logger logger;

    /**
     * Instantiates a new Database processor.
     *
     * @param propertiesStorage the properties storage
     */
    public DatabaseProcessor(PropertiesStorage propertiesStorage) {
        this.propertiesStorage = propertiesStorage;
        this.logger = LogManager.getLogger(DatabaseProcessor.class);

    }

    /**
     * Init connection boolean.
     *
     * @return the boolean
     */
    public boolean initConnection() {
        boolean initiated = false;
        try {
            if (this.connection == null || this.connection.isClosed()) {

                try {
                    this.connection = DriverManager.getConnection("jdbc:postgresql://"
                                    + propertiesStorage.getProperty("db.url"),
                            propertiesStorage.getProperty("db.user"),
                            propertiesStorage.getProperty("db.password"));
                    initiated = true;
                } catch (SQLException e) {
                    logger.error("Connection Failed to:  {}", propertiesStorage.getProperty("db.url"), e);
                }
            }
        } catch (SQLException e) {
            logger.error("Database init connection error", e);
        }
        logger.trace("Database connection established");
        return initiated;
    }

    /**
     * Close connection.
     */
    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            logger.error("Connection closing error", e);
        }
        logger.trace("Database connection was closed");
    }

    /**
     * Put into database boolean.
     *
     * @param vacancies the vacancies
     * @return the boolean
     */
    public boolean putIntoDatabase(HashSet<Vacancy> vacancies) {
        boolean isPut = false;
        try {
            if (connection != null && !connection.isClosed()) {
                connection.setAutoCommit(false);
                String sql = "INSERT INTO vacancies (id, link, topic, name, date)\n"
                        + "VALUES(?, ?, ?, ?, ?)\n"
                        + "ON CONFLICT (id) DO NOTHING;";
                PreparedStatement pstmt = connection.prepareStatement(sql);
                int i = 1;
                for (Vacancy vacancy : vacancies) {
                    pstmt.setInt(1, vacancy.hashCode());
                    pstmt.setString(2, vacancy.getLink());
                    pstmt.setString(3, vacancy.getTopic());
                    pstmt.setString(4, vacancy.getName());
                    pstmt.setLong(5, vacancy.getDate().getTime());
                    pstmt.addBatch();
                    if (i % 1000 == 0 || i == vacancies.size()) {
                        pstmt.executeBatch();
                    }
                    i++;
                }
                connection.commit();
                connection.setAutoCommit(true);
                isPut = true;
            }
        } catch (SQLException e) {
            logger.error("Put data to th DB error", e);
        }
        logger.trace("Data was put to the DB");
        return isPut;
    }

    /**
     * Prepare database structure boolean.
     *
     * @return the boolean
     */
    public boolean prepareDatabaseStructure() {
        boolean isPrepared = false;
        String sqlCreateTable =
                "CREATE TABLE IF NOT EXISTS vacancies"
                    + "(id INTEGER PRIMARY KEY,"
                    + "link VARCHAR(500),"
                    + "topic VARCHAR(500),"
                    + "name VARCHAR(500),"
                    + "date BIGINT);";
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sqlCreateTable);
            isPrepared = true;
        } catch (SQLException e) {
            logger.error("Database structure preparation error", e);
        }
        logger.trace("Database structure was prepared.");
        return isPrepared;
    }
}
