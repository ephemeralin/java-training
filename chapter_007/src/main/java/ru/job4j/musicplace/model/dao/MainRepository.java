package ru.job4j.musicplace.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.musicplace.model.entity.*;
import ru.job4j.utils.PostgresConnectorDBCP;
import ru.job4j.utils.PropertiesStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * The type Main repository.
 */
public class MainRepository {
    /**
     * Main repository instance.
     */
    private static MainRepository instance;
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
    private MainRepository() {
        this.log = LogManager.getLogger(this.getClass());
        final PropertiesStorage propertiesStorage = new PropertiesStorage("/properties-music-place.properties");
        this.databaseConnector = new PostgresConnectorDBCP(propertiesStorage);
        createDatabaseIfNotExist();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized MainRepository getInstance() {
        if (instance == null) {
            instance = new MainRepository();
        }
        return instance;
    }

    /**
     * Prepare database for the first time using.
     */
    private void createDatabaseIfNotExist() {
        String sql = "CREATE TABLE IF NOT EXISTS roles"
                + "(id SERIAL PRIMARY KEY,"
                + "name VARCHAR(100));";
        databaseConnector.executeStatement(sql, "creating table Role");

        sql = "CREATE TABLE IF NOT EXISTS genres"
                + "(id SERIAL PRIMARY KEY,"
                + "name VARCHAR(100));";
        databaseConnector.executeStatement(sql, "creating table Genre");


        sql = "CREATE TABLE IF NOT EXISTS users"
                + "(id SERIAL PRIMARY KEY,"
                + "name VARCHAR(100),"
                + "password VARCHAR(100),"
                + "role_id INT4,"
                + "CONSTRAINT FK_role FOREIGN KEY (role_id) REFERENCES roles(id));";
        databaseConnector.executeStatement(sql, "creating table User");

        sql = "CREATE TABLE IF NOT EXISTS addresses"
                + "(id SERIAL PRIMARY KEY,"
                + "name VARCHAR(100),"
                + "user_id INT4 UNIQUE NOT NULL);";
        databaseConnector.executeStatement(sql, "creating table Address");

        //create 1:1 relationship between User and Address
        sql = "ALTER TABLE addresses "
                + "DROP CONSTRAINT IF EXISTS FK_address_user;";
        databaseConnector.executeStatement(sql, "deleting if exists relationship between User and Address");
        sql = "ALTER TABLE addresses "
                + "ADD CONSTRAINT FK_address_user FOREIGN KEY(user_id) REFERENCES users(id);";
        databaseConnector.executeStatement(sql, "creating relationship between User and Address");

        //create junction table user_genre
        sql = "CREATE TABLE IF NOT EXISTS user_genre"
                + "(user_id INT4,"
                + "genre_id INT4,"
                + "CONSTRAINT user_genre_pk PRIMARY KEY (user_id, genre_id),"
                + "CONSTRAINT FK_user FOREIGN KEY (user_id) REFERENCES users (id),"
                + "CONSTRAINT FK_genre FOREIGN KEY (genre_id) REFERENCES genres (id));";
        databaseConnector.executeStatement(sql, "creating junction table user_genre");
    }

    /**
     * Gets user data.
     *
     * @param user the user
     * @return the user data
     */
    public HashMap<String, Object> getUserData(User user) {
        HashMap<String, Object> userData = new HashMap<>();
        userData.put("User", user);
        userData.put("Role", user.getRole());
        userData.put("Address", findAddressByUser(user));
        userData.put("GenresList", findGenresByUser(user));
        return userData;
    }

    /**
     * Create user with info.
     *
     * @param name          the name
     * @param addressString the address string
     * @param role          the role
     * @param genres        the genres
     * @return the user
     */
    public User createUserWithInfo(String name, String addressString, Role role, List<Genre> genres) {
        if (name.isEmpty()) {
            return null;
        }
        //user
        User user = new User();
        user.setName(name);
        user.setRole(role);
        int userId = UserDAO.getInstance().create(user);
        user.setId(userId);
        //address
        Address address = new Address();
        address.setName(addressString);
        address.setUser(user);
        int addressId = AddressDAO.getInstance().create(address);
        address.setId(addressId);
        user.setAddress(address);
        //genres
        for (Genre genre : genres) {
            addGenreToUser(user, genre);
            user.setGenres(genres);
        }
        return user;
    }

    /**
     * Add genre to user.
     *
     * @param user  the user
     * @param genre the genre
     * @return the boolean
     */
    public boolean addGenreToUser(User user, Genre genre) {
        boolean isAdded = false;
        if (user != null && genre != null) {
            String sql = "INSERT INTO user_genre (user_id, genre_id) VALUES (?, ?);";
            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setInt(1, user.getId());
                pstmt.setInt(2, genre.getId());
                pstmt.executeUpdate();
                isAdded = true;
            } catch (SQLException e) {
                log.error(String.format("SQL error while adding Genre %s to user %s",
                        genre.getName(), user.getName()), e);
            }
        }
        return isAdded;
    }

    /**
     * Find user by address user.
     *
     * @param address the address
     * @return the user
     */
    public User findUserByAddress(Address address) {
        User user = null;
        if (address != null) {
            String sql =
                    "SELECT users.id AS id, " +
                            "users.name AS name, " +
                            "users.role_id AS role_id, " +
                            "roles.name AS role_name "
                            + "FROM users AS users "
                            + "INNER JOIN addresses AS addresses ON users.id = addresses.user_id "
                            + "LEFT OUTER JOIN roles AS roles ON users.role_id = roles.id "
                            + "WHERE addresses.id = ?;";
            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setInt(1, address.getId());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    user = (User) createEntityFromResultSet(rs, User.class.getName());
                    Role role = new Role();
                    role.setId(rs.getInt("role_id"));
                    role.setName(rs.getString("role_name"));
                    user.setRole(role);
                } else {
                    log.error("SQL Error to find user by address in DB");
                }
            } catch (SQLException e) {
                log.error("SQL Error to find user by address in DB");
            }
        }
        return user;
    }

    /**
     * Find users by role list.
     *
     * @param role the role
     * @return the list
     */
    public List<User> findUsersByRole(Role role) {
        ArrayList<User> usersList = new ArrayList<>();
        if (role != null) {
            String sql =
                    "SELECT users.id AS id, "
                            + "users.name AS name "
                            + "FROM users AS users "
                            + "INNER JOIN roles AS roles ON users.role_id = roles.id "
                            + "WHERE roles.id = ?;";
            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setInt(1, role.getId());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    User user = (User) createEntityFromResultSet(rs, User.class.getName());
                    user.setRole(role);
                    usersList.add(user);
                }
            } catch (SQLException e) {
                log.error("SQL Error to find user by role in DB");
            }
        }
        return usersList;
    }

    /**
     * Find users by genre list.
     *
     * @param genre the genre
     * @return the list
     */
    public List<User> findUsersByGenre(Genre genre) {
        ArrayList<User> usersList = new ArrayList<>();
        if (genre != null) {
            String sql =
                    "SELECT users.id AS id, "
                            + "users.name AS name, "
                            + "roles.id AS role_id, "
                            + "roles.name AS role_name "
                            + "FROM users AS users "
                            + "INNER JOIN roles AS roles ON users.role_id = roles.id "
                            + "INNER JOIN user_genre AS ug ON users.id = ug.user_id "
                            + "WHERE ug.genre_id = ?;";
            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setInt(1, genre.getId());
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    User user = (User) createEntityFromResultSet(rs, User.class.getName());
                    Role role = new Role();
                    role.setId(rs.getInt("role_id"));
                    role.setName(rs.getString("role_name"));
                    user.setRole(role);
                    usersList.add(user);
                }
            } catch (SQLException e) {
                log.error("SQL Error to find users by genre in DB");
            }
        }
        return usersList;
    }

    /**
     * Find address by user address.
     *
     * @param user the user
     * @return the address
     */
    public Address findAddressByUser(User user) {
        Address address = null;
        if (user != null) {
            String sql =
                    "SELECT addresses.id AS id, addresses.name AS name "
                            + "FROM addresses AS addresses "
                            + "WHERE addresses.user_id = ?;";
            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setInt(1, user.getId());
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    address = (Address) createEntityFromResultSet(rs, Address.class.getName());
                    address.setUser(user);
                } else {
                    log.error("There is address by user id in DB");
                }
            } catch (SQLException e) {
                log.error("SQL Error to find address by user id in DB");
            }
        }
        return address;
    }

    /**
     * Find genres by user list.
     *
     * @param user the user
     * @return the list
     */
    public List<Genre> findGenresByUser(User user) {
        ArrayList<Genre> genresList = new ArrayList<>();
        String sql =
                "SELECT user_genre.genre_id AS genre_id, "
                        + "genres.name AS genre_name "
                        + "FROM user_genre AS user_genre "
                        + "INNER JOIN genres AS genres ON user_genre.genre_id = genres.id "
                        + "WHERE user_genre.user_id = ?;";
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, user.getId());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int genre_id = rs.getInt("genre_id");
                String genre_name = rs.getString("genre_name");
                Genre genre = new Genre();
                genre.setId(genre_id);
                genre.setName(genre_name);
                genresList.add(genre);
            }
        } catch (SQLException e) {
            log.error("SQL Error to find genres for user %s", user.getName());
        }
        return genresList;
    }

    /**
     * Create Entity from the ResultSet.
     *
     * @param rs        ResultSet
     * @param className Name of class
     * @return Entity entity
     * @throws Exception exception
     */
    public IEntity createEntityFromResultSet(ResultSet rs, String className) {
        IEntity entity = null;
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
             entity = (IEntity) Class.forName(className).getConstructor().newInstance();
            entity.setId(id);
            entity.setName(name);
            return entity;
        } catch (Exception e) {
            log.error("Error while creating entity from result set");
        }
        return entity;
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
        if (!login.isEmpty()) {
            String sql =
                    "SELECT users.id AS id, "
                            + "users.name AS name "
                            + "FROM users AS users "
                            + "WHERE users.name = ? AND users.password = ?;";
            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setString(1, login);
                pstmt.setString(2, password);
                ResultSet rs = pstmt.executeQuery();
                if (rs.next()) {
                    isIdentified = true;
                }
            } catch (SQLException e) {
                log.error("SQL Error while authenticates");
            }
        }
        return isIdentified;
    }
}
