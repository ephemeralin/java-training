package com.ephemeralin.carplace.dao;

import com.ephemeralin.carplace.model.Role;
import com.ephemeralin.carplace.model.User;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * The type User dao.
 */
@Repository
@Log4j2
public class UserDAO extends DAO<User> implements IDAO<User> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int create(User entity) {
        getCurrentSession().save(entity);
        return entity.getId();
    }

    @Override
    public User findById(int id) {
        return getCurrentSession().get(User.class, id);
    }

    @Override
    public List findAll() {
        return getCurrentSession().createQuery("FROM User u JOIN FETCH u.role").list();
    }

    @Override
    public User update(User entity) {
        User entityUpdate = getCurrentSession().load(User.class, entity.getId());
        entityUpdate.setLogin(entity.getLogin());
        return entityUpdate;
    }

    @Override
    public boolean delete(int id) {
        User entity = findById(id);
        return super.delete(sessionFactory, entity);
    }

    /**
     * Is identified boolean.
     *
     * @param login    the login
     * @param password the password
     * @return the boolean
     */
    public boolean isIdentified(String login, String password) {
        Query query = getCurrentSession().createQuery("FROM User u JOIN FETCH u.role WHERE u.login = :login AND u.password = :password");
        query.setParameter("login", login);
        query.setParameter("password", password);
        return !query.list().isEmpty();
    }

    /**
     * Gets role by login.
     *
     * @param login the login
     * @return the role by login
     */
    public Role getRoleByLogin(String login) {
        Query query = getCurrentSession().createQuery("SELECT role FROM User WHERE login = :login");
        query.setParameter("login", login);
        return (Role) query.getSingleResult();
    }

    /**
     * Find by login user.
     *
     * @param login the login
     * @return the user
     */
    public User findByLogin(String login) {
        Query query = getCurrentSession().createQuery("FROM User u u JOIN FETCH u.role WHERE u.login = :login");
        query.setParameter("login", login);
        return (User) query.getSingleResult();
    }

    /**
     * Find by criteria list.
     *
     * @param criterias the criterias
     * @return the list
     */
    public List findByCriteria(HashMap<String, Object> criterias) {
        return super.findByCriteria(sessionFactory, criterias);
    }
}
