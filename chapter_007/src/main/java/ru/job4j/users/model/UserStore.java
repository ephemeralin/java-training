package ru.job4j.users.model;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.utils.PostgresConnectorDBCP;
import ru.job4j.utils.PropertiesStorage;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User store.
 */
public final class UserStore {
    /**
     * User store.
     */
    private static UserStore instance;
    /**
     * Database connector.
     */
    private final PostgresConnectorDBCP databaseConnector;
    /**
     * Logger instance.
     */
    private Logger log;

    /**
     * Private constructor.
     */
    private UserStore() {
        final PropertiesStorage propertiesStorage = new PropertiesStorage("/properties.properties");
        this.databaseConnector = new PostgresConnectorDBCP(propertiesStorage);
        this.log = LogManager.getLogger(this.getClass());
        createDatabaseIfNotExist();
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
    private void createDatabaseIfNotExist() {
        String sql = "CREATE TABLE IF NOT EXISTS users"
                + "(email VARCHAR(100) PRIMARY KEY,"
                + "name VARCHAR(200),"
                + "login VARCHAR(100),"
                + "created BIGINT);";
        try (Statement stmt = databaseConnector.getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            log.error("Creating database error", e);
        }
    }

    /**
     * Gets user by email.
     *
     * @param email the email
     * @return the by email
     */
    public User getByEmail(String email) {
        User user = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE email = ?";
        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, email);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                user = getUserFromResultSet(rs);
            }
        } catch (SQLException e) {
            log.error("Error getting user by e-mail", e);
        } finally {
            closeSqlResources(conn, pstmt, rs);
        }
        return user;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        String sql = "SELECT * FROM users";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn =  databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                allUsers.add(getUserFromResultSet(rs));
            }
        } catch (SQLException e) {
            log.error("Error getting all users", e);
        } finally {
            closeSqlResources(conn, pstmt, rs);
        }
        return allUsers;
    }

    /**
     * Add user.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean add(User user) {
        if (user.getEmail().isEmpty()) {
            return false;
        }
        String sql = "INSERT INTO users (email, name, login, created) VALUES (?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isAdded = false;
        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
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
        } finally {
            closeSqlResources(conn, pstmt, rs);
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
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isUpdated = false;
        String sql = "UPDATE users SET name = ?, login = ?, created = ? WHERE email = ?";
        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getLogin());
            pstmt.setLong(3, user.getCreated());
            pstmt.setString(4, user.getEmail());
            pstmt.execute();
            isUpdated = true;
            log.info(String.format("User with e-mail %s updated", user.getEmail()));
        } catch (SQLException e) {
            log.error(String.format("SQL Error updating user with e-mail %s", user.getEmail()), e);
        } finally {
            closeSqlResources(conn, pstmt, rs);
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
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        boolean isDeleted = false;
        String sql = "DELETE FROM users WHERE email = ?";
        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
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

    /**
     * Close all SQL connection resources.
     * @param conn Connection
     * @param pstmt Prepared Statement
     * @param rs Result Set
     */
    private void closeSqlResources(Connection conn, PreparedStatement pstmt, ResultSet rs) {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception e) {
        }
        try {
            if (pstmt != null) {
                pstmt.close();
            }
        } catch (Exception e) {
        }
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (Exception e) {
        }
    }

    @Override
    public String toString() {
        return "UserStore{"
                + "databaseConnector=" + databaseConnector
                + '}';
    }

    /**
     * Destroy.
     */
    public void destroy() {

    }
}
