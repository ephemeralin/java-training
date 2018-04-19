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
        String sql = "CREATE TABLE IF NOT EXISTS roles"
                + "(id INT4 PRIMARY KEY,"
                + "name VARCHAR(100));";
        try (Statement stmt = databaseConnector.getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            log.error("Creating database error (errors)", e);
        }

        sql = "CREATE TABLE IF NOT EXISTS countries"
                + "(id SERIAL PRIMARY KEY,"
                + "name VARCHAR(150));";
        try (Statement stmt = databaseConnector.getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            log.error("Creating database error (countries)", e);
        }

        sql = "CREATE TABLE IF NOT EXISTS cities"
                + "(id SERIAL PRIMARY KEY,"
                + "name VARCHAR(150),"
                + "country_id INT4,"
                + "FOREIGN KEY (country_id) REFERENCES countries(id));";
        try (Statement stmt = databaseConnector.getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            log.error("Creating database error (cities)", e);
        }

        sql = "CREATE TABLE IF NOT EXISTS users"
                + "(email VARCHAR(100) PRIMARY KEY,"
                + "name VARCHAR(200),"
                + "login VARCHAR(100),"
                + "password VARCHAR(100),"
                + "created BIGINT,"
                + "role_id INT4,"
                + "FOREIGN KEY (role_id) REFERENCES roles(id));";
        try (Statement stmt = databaseConnector.getConnection().createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            log.error("Creating database error (users)", e);
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
        String sql =
            "SELECT "
                + "users.email AS email,"
                + "users.name AS name,"
                + "users.login AS login,"
                + "users.created AS created,"
                + "users.password AS password,"
                + "roles.name AS role_name,"
                + "roles.id AS role_id, "
                + "cities.id AS city_id, "
                + "cities.name AS city_name, "
                + "countries.id AS country_id, "
                + "countries.name AS country_name "
                + "FROM users AS users "
                + "LEFT OUTER JOIN roles AS roles ON users.role_id = roles.id "
                + "LEFT OUTER JOIN cities AS cities ON users.city_id = cities.id "
                + "LEFT OUTER JOIN countries AS countries ON cities.country_id = countries.id "
                + "where users.email = ?;";
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
     * Gets role by login.
     *
     * @param login the login
     * @return the role by login
     */
    public Role getRoleByLogin(String login) {
        Connection conn = null;
        PreparedStatement pstmt = null;
        Role role = null;
        ResultSet rs = null;
        String sql =
            "SELECT "
                + "users.email AS email,"
                + "users.name AS name,"
                + "users.login AS login,"
                + "users.created AS created,"
                + "users.password AS password,"
                + "roles.name AS role_name,"
                + "roles.id AS role_id, "
                + "cities.id AS city_id, "
                + "cities.name AS city_name, "
                + "countries.id AS country_id, "
                + "countries.name AS country_name "
                + "FROM users AS users "
                + "LEFT OUTER JOIN roles AS roles ON users.role_id = roles.id "
                + "LEFT OUTER JOIN cities AS cities ON users.city_id = cities.id "
                + "LEFT OUTER JOIN countries AS countries ON cities.country_id = countries.id "
                + "where users.login = ?;";
        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, login);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                User user = getUserFromResultSet(rs);
                if (user != null) {
                    role = user.getRole();
                }
            }
        } catch (SQLException e) {
            log.error("Error getting user by e-mail", e);
        } finally {
            closeSqlResources(conn, pstmt, rs);
        }
        if (role == null) {
            role = new Role(3, "guest");
        }
        return role;
    }

    /**
     * Gets role by role name.
     *
     * @param roleName the role name
     * @return the role by role name
     */
    public Role getRoleByRoleName(String roleName) {
        Role role = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql =
                "SELECT "
                        + "roles.id AS id,"
                        + "roles.name AS name "
                        + "FROM roles AS roles "
                        + "where roles.name = ?;";
        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, roleName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                role = new Role(rs.getInt("id"), rs.getString("name"));
            }
        } catch (SQLException e) {
            log.error("Error getting role by name", e);
        } finally {
            closeSqlResources(conn, pstmt, rs);
        }
        return role;
    }

    /**
     * Gets city by city name.
     *
     * @param cityName the city name
     * @return the city by city name
     */
    public City getCityByCityName(String cityName) {
        City city = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql =
                "SELECT "
                        + "cities.id AS id,"
                        + "cities.name AS name, "
                        + "countries.id AS country_id, "
                        + "countries.name AS country_name "
                        + "FROM cities AS cities "
                        + "LEFT OUTER JOIN countries AS countries ON cities.country_id = countries.id "
                        + "where cities.name = ?;";
        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, cityName);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Country country = new Country(rs.getInt("country_id"), rs.getString("country_name"));
                city = new City(rs.getInt("id"), rs.getString("name"), country);
            }
        } catch (SQLException e) {
            log.error("Error getting city by name", e);
        } finally {
            closeSqlResources(conn, pstmt, rs);
        }
        return city;
    }

    /**
     * Gets city by id.
     *
     * @param id the id
     * @return the city by id
     */
    public City getCityById(String id) {
        City city = null;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql =
                "SELECT "
                        + "cities.id AS id,"
                        + "cities.name AS name, "
                        + "countries.id AS country_id, "
                        + "countries.name AS country_name "
                        + "FROM cities AS cities "
                        + "LEFT OUTER JOIN countries AS countries ON cities.country_id = countries.id "
                        + "where cities.id = ?;";
        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, Integer.parseInt(id));
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Country country = new Country(rs.getInt("country_id"), rs.getString("country_name"));
                city = new City(rs.getInt("id"), rs.getString("name"), country);
            }
        } catch (SQLException e) {
            log.error("Error getting city by id", e);
        } finally {
            closeSqlResources(conn, pstmt, rs);
        }
        return city;
    }

    /**
     * Gets all.
     *
     * @return the all
     */
    public List<User> getAll() {
        List<User> allUsers = new ArrayList<>();
        String sql =
            "SELECT "
                + "users.email AS email,"
                + "users.name AS name,"
                + "users.login AS login,"
                + "users.created AS created,"
                + "users.password AS password,"
                + "roles.name AS role_name,"
                + "roles.id AS role_id, "
                + "cities.id AS city_id, "
                + "cities.name AS city_name, "
                + "countries.id AS country_id, "
                + "countries.name AS country_name "
                + "FROM users AS users "
                + "LEFT OUTER JOIN roles AS roles ON users.role_id = roles.id "
                + "LEFT OUTER JOIN cities AS cities ON users.city_id = cities.id "
                + "LEFT OUTER JOIN countries AS countries ON cities.country_id = countries.id ";

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
        String sql = "INSERT INTO users (email, name, login, password, created, role_id, city_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
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
            pstmt.setString(4, user.getPassword());
            pstmt.setLong(5, user.getCreated());
            pstmt.setInt(6, user.getRole().getId());
            pstmt.setInt(7, user.getCity().getId());
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
        String sql =
            "UPDATE users "
            + "SET name = ?, login = ?, created = ?, password = ?, role_id = ?, city_id = ? "
            + "WHERE email = ?";
        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getLogin());
            pstmt.setLong(3, user.getCreated());
            pstmt.setString(4, user.getPassword());
            pstmt.setInt(5, user.getRole().getId());
            pstmt.setInt(6, user.getCity().getId());
            pstmt.setString(7, user.getEmail());
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
        boolean isDeleted = false;
        String sql = "DELETE FROM users WHERE email = ?";
        try {
            Connection conn = databaseConnector.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql);
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
     * Is identified boolean.
     *
     * @param login    the login
     * @param password the password
     * @return the boolean
     */
    public boolean isIdentified(String login, String password) {
        boolean isIdentified = false;
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql = "SELECT * FROM users WHERE login = ? AND password = ?";
        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, login);
            pstmt.setString(2, password);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                isIdentified = true;
            }
        } catch (SQLException e) {
            log.error("Error checking login and password", e);
        } finally {
            closeSqlResources(conn, pstmt, rs);
        }
        return isIdentified;
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
            String password = rs.getString("password");
            Long created = rs.getLong("created");
            //role data
            int role_id = rs.getInt("role_id");
            String role_name = rs.getString("role_name");
            Role role = new Role(role_id, role_name);
            //country data
            int country_id = rs.getInt("country_id");
            String country_name = rs.getString("country_name");
            Country country = new Country(country_id, country_name);
            //city data
            int city_id = rs.getInt("city_id");
            String city_name = rs.getString("city_name");
            City city = new City(city_id, city_name, country);
            user = new User(name, login, email, created, password, role, city);
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

    /**
     * Gets all roles.
     *
     * @return the all roles
     */
    public List<Role> getAllRoles() {
        List<Role> allRoles = new ArrayList<>();
        String sql =
                "SELECT "
                        + "roles.id AS id,"
                        + "roles.name AS name "
                        + "FROM roles AS roles;";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn =  databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                allRoles.add(new Role(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            log.error("Error getting all roles", e);
        } finally {
            closeSqlResources(conn, pstmt, rs);
        }
        return allRoles;
    }

    /**
     * Gets all countries.
     *
     * @return the all countries
     */
    public List<Country> getAllCountries() {
        List<Country> allCountries = new ArrayList<>();
        String sql =
                "SELECT "
                        + "countries.id AS id,"
                        + "countries.name AS name "
                        + "FROM countries AS countries "
                        + "ORDER BY name ASC;";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn =  databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                allCountries.add(new Country(rs.getInt("id"), rs.getString("name")));
            }
        } catch (SQLException e) {
            log.error("Error getting all countries", e);
        } finally {
            closeSqlResources(conn, pstmt, rs);
        }
        return allCountries;
    }

    /**
     * Gets cities by country id.
     *
     * @param countryId the country id
     * @return the cities by country id
     */
    public List<City> getCitiesByCountryId(int countryId) {
        List<City> cities = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        String sql =
                "SELECT "
                        + "cities.id AS id,"
                        + "cities.name AS name, "
                        + "countries.id AS country_id, "
                        + "countries.name AS country_name "
                        + "FROM cities AS cities "
                        + "INNER JOIN countries AS countries ON cities.country_id = countries.id "
                        + "where countries.id = ?;";
        try {
            conn = databaseConnector.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, countryId);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Country country = new Country(rs.getInt("country_id"), rs.getString("country_name"));
                City city = new City(rs.getInt("id"), rs.getString("name"), country);
                cities.add(city);
            }
        } catch (SQLException e) {
            log.error("Error getting cities for country id", e);
        } finally {
            closeSqlResources(conn, pstmt, rs);
        }
        return cities;
    }
}
