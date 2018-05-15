package ru.job4j.musicplace.model.dao;

import ru.job4j.musicplace.model.entity.IEntity;
import ru.job4j.musicplace.model.entity.Role;
import ru.job4j.musicplace.model.entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type User dao.
 */
public class UserDAO extends BaseDAO implements IModelDAO<User> {
    /**
     * Main repository instance.
     */
    private static UserDAO instance;

    /**
     * Default constructor.
     */
    private UserDAO() {
        super();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized UserDAO getInstance() {
        if (instance == null) {
            instance = new UserDAO();
        }
        return instance;
    }

    @Override
    public int create(User user) {
        int id = 0;
        String sql = "INSERT INTO users (id, name, password, role_id) VALUES (DEFAULT, ?, ?, ?) RETURNING id;";
        try (Connection conn = getDatabaseConnector().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getPassword());
            pstmt.setInt(3, user.getRole().getId());
            id = super.create(user, pstmt);
        } catch (SQLException e) {
            getLog().error(String.format("SQL Error to put User with name %s to the DB", user.getName()), e);
        }
        return id;
    }

    @Override
    public User findById(int id) {
        User user = null;
        String sql =
                "SELECT users.id AS id, users.name AS name, users.role_id AS role_id, roles.name AS role_name "
                        + "FROM users AS users "
                        + "LEFT OUTER JOIN roles AS roles ON users.role_id = roles.id "
                        + "WHERE users.id = ?;";
        try (Connection conn = getDatabaseConnector().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = new User();
                user.setId(id);
                user.setName(rs.getString("name"));
                Role role = new Role();
                role.setId(rs.getInt("role_id"));
                role.setName(rs.getString("role_name"));
                user.setRole(role);
                getLog().info(String.format("User with id %s found in DB", id));
            } else {
                getLog().error(String.format("SQL Error to find user with id %s in DB", id));
            }
        } catch (SQLException e) {
            getLog().error(String.format("SQL Error to find user with id %s in DB", id));
        }
        return user;
    }

    @Override
    public User findByName(String name) {
        User user = null;
        String sql =
                "SELECT users.id AS id, users.name AS name, users.role_id AS role_id, roles.name AS role_name "
                        + "FROM users AS users "
                        + "LEFT OUTER JOIN roles AS roles ON users.role_id = roles.id "
                        + "WHERE users.name = ?;";
        try (Connection conn = getDatabaseConnector().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                user = new User();
                user.setId(id);
                user.setName(name);
                Role role = new Role();
                role.setId(rs.getInt("role_id"));
                role.setName(rs.getString("role_name"));
                user.setRole(role);
                getLog().info(String.format("User with name %s found in DB", name));
            } else {
                getLog().error(String.format("SQL Error to find user with name %s in DB", name));
            }
        } catch (SQLException e) {
            getLog().error(String.format("SQL Error to find user with name %s in DB", name));
        }
        return user;
    }

    @Override
    public List<? extends IEntity> findAll() {
        ArrayList<User> usersList = new ArrayList<>();
        String sql =
                "SELECT users.id AS id, users.name AS name, users.role_id AS role_id, roles.name AS role_name "
                        + "FROM users AS users "
                        + "LEFT OUTER JOIN roles AS roles ON users.role_id = roles.id;";
        try (Connection conn = getDatabaseConnector().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setName(rs.getString("name"));
                Role role = new Role();
                role.setId(rs.getInt("role_id"));
                role.setName(rs.getString("role_name"));
                user.setRole(role);
                usersList.add(user);
                getLog().info(String.format("User with id %s found in DB", user.getId()));
            }
        } catch (SQLException e) {
            getLog().error(String.format("SQL Error to find all users in DB"));
        }
        return usersList;
    }

    @Override
    public boolean update(User user) {
        boolean isUpdated = false;
        String sql =
                "UPDATE users "
                        + "SET name = ?, role_id = ?"
                        + "WHERE id = ?";
        try (Connection conn = getDatabaseConnector().getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getRole().getId());
            pstmt.setInt(3, user.getId());
            isUpdated = super.update(user, pstmt);
        } catch (SQLException e) {
            getLog().error(String.format("SQL Error while updating User with name %s to the DB", user.getName()), e);
        }
        return isUpdated;
    }

    @Override
    public boolean delete(User user) {
        return super.delete(user, "users");
    }
}
