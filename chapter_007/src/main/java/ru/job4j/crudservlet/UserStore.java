package ru.job4j.crudservlet;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.utils.PostgresConnector;
import ru.job4j.utils.PropertiesStorage;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * The type User store.
 */
public class UserStore {
    /**
     * User store.
     */
    private static UserStore instance;
    /**
     * Database connection.
     */
    private final PostgresConnector databaseConnection;
    /**
     * Logger instance.
     */
    private Logger log;

    /**
     * Private constructor.
     */
    private UserStore() {
        final PropertiesStorage propertiesStorage = new PropertiesStorage("/properties.properties");
        this.databaseConnection = new PostgresConnector(propertiesStorage);
        this.databaseConnection.getConnection();
        this.log = LogManager.getLogger(this.getClass());
        prepare();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized UserStore getInstance() {
        if (instance == null) {
            instance = new UserStore();
        }
        return instance;
    }

    /**
     * Prepare database for the first time using.
     */
    private void prepare() {
        String sql = "CREATE TABLE IF NOT EXISTS users"
                + "(email VARCHAR(100) PRIMARY KEY,"
                + "name VARCHAR(200),"
                + "login VARCHAR(100),"
                + "created BIGINT);";
        this.databaseConnection.executeStatement(sql);
    }

    /**
     * Close db connection.
     */
    public void destroy() {
        this.databaseConnection.close();
    }

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the by email
     */
    public User getByEmail(String email) {
        User user = null;
        String sql = "SELECT * FROM users WHERE email = ?";
        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, email);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = getUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            log.error("Error getting user by e-mail");
        }
        return user;
    }

    /**
     * Add user.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean add(User user) {
        String sql = "INSERT INTO users (email, name, login, created) VALUES (?, ?, ?, ?)";
        boolean isAdded = false;
        try {
            PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getName());
            pstmt.setString(3, user.getLogin());
            pstmt.setLong(4, user.getCreated());
            pstmt.execute();
            isAdded = true;
            log.info(String.format("User with e-mail %s put to the DB", user.getEmail()));
        } catch (SQLException e) {
            log.error(String.format("SQL Error to put User with e-mail %s to the DB", user.getEmail()), e);
        } catch (Exception e) {
            log.error(String.format("Unknown Error to put User with e-mail %s to the DB", user.getEmail()), e);
        }
        return isAdded;
    }

    /**
     * Update user.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean update(User user) {
        boolean isUpdated = false;
        String sql = "UPDATE users SET name = ?, login = ?, created = ? WHERE email = ?";
        try {
            PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getLogin());
            pstmt.setLong(3, user.getCreated());
            pstmt.setString(4, user.getEmail());
            pstmt.execute();
            isUpdated = true;
            log.info(String.format("User with e-mail %s updated", user.getEmail()));
        } catch (SQLException e) {
            log.error(String.format("SQL Error updating user with e-mail %s", user.getEmail()), e);
        }
        return isUpdated;
    }

    /**
     * Delete user by email.
     *
     * @param email the email
     * @return the boolean
     */
    public boolean deleteByEmail(String email) {
        boolean isDeleted = false;
        String sql = "DELETE FROM users WHERE email = ?";
        try (PreparedStatement pstmt = databaseConnection.getConnection().prepareStatement(sql)) {
            pstmt.setString(1, email);
            pstmt.execute();
            isDeleted = true;
            log.info(String.format("User with e-mail %s deleted", email));
        } catch (SQLException e) {
            log.error(String.format("SQL Error deleting user with e-mail %s", email), e);
        }
        return isDeleted;
    }

    /**
     * Get User object from the Result set of query.
     * @param rs result set
     * @return item
     */
    private User getUserFromResultSet(ResultSet rs) {
        User user = null;
        try {
            String name = rs.getString("name");
            String email = rs.getString("email");
            String login = rs.getString("login");
            Long created = rs.getLong("created");
            user = new User(name, login, email, created);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return user;
    }

    @Override
    public String toString() {
        return "UserStore{"
                + "databaseConnection=" + databaseConnection
                + '}';
    }
}
