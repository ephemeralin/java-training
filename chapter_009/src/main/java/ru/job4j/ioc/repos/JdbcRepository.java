package ru.job4j.ioc.repos;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import ru.job4j.util.PostgresConnectorDBCP;
import ru.job4j.util.PropertiesStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * JDBC repository.
 */
@Log4j2
@Repository
@Qualifier("jdbcRepository")
public class JdbcRepository implements DAO {

    private static JdbcRepository instance;

    private final PostgresConnectorDBCP databaseConnector =
            new PostgresConnectorDBCP(new PropertiesStorage("/properties.properties"));


    /**
     * Private constructor.
     */
    private JdbcRepository() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized JdbcRepository getInstance() {
        if (instance == null) {
            instance = new JdbcRepository();
        }
        return instance;
    }

    @Override
    public int create(User user) {
        int id = 0;
        String sql = "INSERT INTO users (id, name) VALUES (DEFAULT, ?) RETURNING id;";
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
        ) {
            pstmt.setString(1, user.getName());
            ResultSet rs = null;
                pstmt.executeUpdate();
                rs = pstmt.getGeneratedKeys();
                if (rs != null && rs.next()) {
                    id = rs.getInt(1);
                } else {
                    log.error(String.format("SQL Error to put user with name %s to the DB", user.getName()));
                }
        } catch (SQLException e) {
            log.error(String.format("SQL Error to put user with name %s to the DB", user.getName()), e);
        }
        return id;
    }

    @Override
    public User findById(int id) {
        String sql =
                "SELECT * FROM users WHERE users.id = ?;";
        User user = null;
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                user = createEntityFromResultSet(rs);
            } else {
                log.error(String.format("SQL Error to find user with id %s in DB", id));
            }
        } catch (SQLException e) {
            log.error(String.format("SQL Error to find user with id %s in DB", user));
        }
        return user;
    }

    @Override
    public User update(User user) {
        User newUser = null;
        String sql = "UPDATE users SET name = ? WHERE id = ?";
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, user.getName());
            pstmt.setInt(2, user.getId());
            pstmt.execute();
            newUser = user;
        } catch (SQLException e) {
            log.error(String.format("SQL Error while updating user with name %s to the DB", user.getName()), e);
        }
        return newUser;
    }

    @Override
    public boolean delete(User user) {
        boolean isDeleted = false;
        String sql = "DELETE FROM users WHERE id = ?";
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, user.getId());
            pstmt.execute();
            isDeleted = true;
        } catch (SQLException e) {
            log.error(String.format("SQL Error deleting user with name %s in the DB", user.getName()), e);
        }
        return isDeleted;
    }

    /**
     * Create Entity from the ResultSet.
     * @param rs ResultSet
     * @return Entity
     * @throws Exception exception
     */
    private User createEntityFromResultSet(ResultSet rs) {
        User user = null;
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            user = new User();
            user.setId(id);
            user.setName(name);
        } catch (Exception e) {
            log.error("Error while creating entity from result set", e);
        }
        return user;
    }
}
