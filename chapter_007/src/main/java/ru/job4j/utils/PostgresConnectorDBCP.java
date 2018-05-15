package ru.job4j.utils;

import org.apache.commons.dbcp2.*;
import org.apache.commons.pool2.ObjectPool;
import org.apache.commons.pool2.impl.GenericObjectPool;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.sql.DataSource;
import java.sql.*;

/**
 * The type Postgres database connection.
 */
public final class PostgresConnectorDBCP {

    /**
     * Logger instance.
     */
    private final Logger log;
    /**
     * Properties storage.
     */
    private final PropertiesStorage propertiesStorage;

    /**
     * Data source.
     */
    private DataSource dataSource;

    /**
     * Instantiates a new Postgres connector.
     *
     * @param propertiesStorage the properties storage
     */
    public PostgresConnectorDBCP(PropertiesStorage propertiesStorage) {
        this.log = LogManager.getLogger(this.getClass());
        this.propertiesStorage = propertiesStorage;
        prepareDatasource();
    }

    /**
     * Prepare data source.
     */
    private void prepareDatasource() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            log.error("Where is your PostgreSQL JDBC Driver? Include in your library path!", e);
        }
        String dbUrl = "jdbc:postgresql://" + propertiesStorage.getProperty("db.url");
        String dbUser = propertiesStorage.getProperty("db.user");
        String dbPass = propertiesStorage.getProperty("db.password");
        ConnectionFactory connectionFactory =
                new DriverManagerConnectionFactory(dbUrl, dbUser, dbPass);
        PoolableConnectionFactory poolableConnectionFactory =
                new PoolableConnectionFactory(connectionFactory, null);
        ObjectPool<PoolableConnection> connectionPool =
                new GenericObjectPool<>(poolableConnectionFactory);
        poolableConnectionFactory.setPool(connectionPool);
        this.dataSource = new PoolingDataSource<>(connectionPool);
        log.info("Database connection established: {}", dbUrl);
    }

    /**
     * Gets connection.
     *
     * @return the connection
     */
    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = dataSource.getConnection();
        } catch (SQLException e) {
            log.error("Database init connection error", e);
        }
        return connection;
    }

    @Override
    public String toString() {
        return "PostgresConnector{"
                + "connection=" + dataSource
                + '}';
    }

    /**
     * Execute statement.
     *
     * @param sql           the sql
     * @param statementName the statement name
     */
    public void executeStatement(String sql, String statementName) {
        try (Statement stmt = getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            log.error("Executing statement error with: " + statementName, e);
        }
    }
}
