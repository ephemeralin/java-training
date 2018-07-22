package ru.job4j.util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * The type Postgres database connection.
 */
public final class PostgresConnector {

    /**
     * Logger instance.
     */
    private final Logger log;
    /**
     * Properties storage.
     */
    private final PropertiesStorage propertiesStorage;
    /**
     * Connection.
     */
    private Connection connection;

    /**
     * Instantiates a new Postgres connector.
     *
     * @param propertiesStorage the properties storage
     */
    public PostgresConnector(PropertiesStorage propertiesStorage) {
        this.propertiesStorage = propertiesStorage;
        this.log = LogManager.getLogger(this.getClass());
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.error("Where is your PostgreSQL JDBC Driver? Include in your library path!", e);
        }
        try {
            if (this.connection == null || this.connection.isClosed()) {
                String dbUrl = "jdbc:postgresql://" + propertiesStorage.getProperty("db.url");
                String dbUser = propertiesStorage.getProperty("db.user");
                String dbPass = propertiesStorage.getProperty("db.password");
                try {
                    this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPass);
                    log.info("Database connection established: {}", dbUrl);
                } catch (SQLException e) {
                    log.error("Connection Failed to:  {}", dbUrl, e);
                }
            }
        } catch (SQLException e) {
            log.error("Database init connection error", e);
        }
        return this.connection;
    }

    /**
     * Close connection.
     */
    public void close() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            log.error("Connection closing error", e);
        }
        log.info("Database connection closed. Bye!");
    }

    /**
     * Prepare table boolean.
     *
     * @param sql the sql
     * @return the boolean
     */
    public boolean executeStatement(String sql) {
        boolean isExecuted = false;
        try (Statement stmt = this.connection.createStatement()) {
            stmt.execute(sql);
            isExecuted = true;
        } catch (SQLException e) {
            log.error("SQL execution statement error!", e);
        }
        return isExecuted;
    }

    @Override
    public String toString() {
        return "PostgresConnector{"
                + "connection=" + connection
                + '}';
    }
}
