package ru.job4j.carplace.model.dao;

import org.hibernate.query.Query;
import ru.job4j.carplace.model.entity.Role;
import ru.job4j.carplace.model.entity.User;

import java.sql.SQLException;
import java.util.List;

/**
 * The type User dao.
 */
@lombok.extern.log4j.Log4j2
public class UserDAO extends DAO implements IModelDAO<User> {
    /**
     * Item DAO instance.
     */
    private static UserDAO instance;

    /**
     * Default constructor.
     */
    private UserDAO() {
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
    public int create(User user) throws SQLException {
        return super.tx(
                session -> {
                    session.save(user);
                    log.info("Successfully created " + user.toString());
                    return user.getId();
                }
        );
    }

    @Override
    public User findById(int id) {
        return super.tx(
                session -> session.get(User.class, id)
        );
    }

    @Override
    public List findAll() {
        return this.tx(
                session -> session.createQuery("FROM User ").list()
        );
    }

    @Override
    public User update(User user) {
        return this.tx(
                session -> {
                    User userUpdate = session.load(User.class, user.getId());
                    userUpdate.setLogin(user.getLogin());
                    log.info("Successfully updated " + user.toString());
                    return userUpdate;
                }
        );
    }

    @Override
    public boolean delete(int id) {
        return this.tx(
                session -> {
                    boolean success = false;
                    User user = findById(id);
                    if (user != null) {
                        session.delete(user);
                        success = true;
                        log.info("Successfully deleted " + user.toString());
                    }
                    return success;
                }
        );
    }

    /**
     * Is identified boolean.
     *
     * @param login    the login
     * @param password the password
     * @return the boolean
     */
    public boolean isIdentified(String login, String password) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("FROM User WHERE login = :login and password = :password");
                    query.setParameter("login", login);
                    query.setParameter("password", password);
                    return !query.list().isEmpty();
                }
        );
    }

    /**
     * Gets role by login.
     *
     * @param login the login
     * @return the role by login
     */
    public Role getRoleByLogin(String login) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("select role FROM User WHERE login = :login");
                    query.setParameter("login", login);
                    return (Role) query.getSingleResult();
                }
        );
    }

    /**
     * Find by login user.
     *
     * @param login the login
     * @return the user
     */
    public User findByLogin(String login) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("FROM User WHERE login = :login");
                    query.setParameter("login", login);
                    return (User) query.getSingleResult();
                }
        );
    }
}
