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
        sessionFactory.openSession().save(entity);
        return entity.getId();
    }

    @Override
    public User findById(int id) {
        return sessionFactory.openSession().get(User.class, id);
    }

    @Override
    public List findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM User ").list();
    }

    @Override
    public User update(User entity) {
        Session session = sessionFactory.openSession();
        User entityUpdate = session.load(User.class, entity.getId());
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
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User WHERE login = :login and password = :password");
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
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("select role FROM User WHERE login = :login");
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
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM User WHERE login = :login");
        query.setParameter("login", login);
        return (User) query.getSingleResult();
    }

    public List findByCriteria(HashMap<String, Object> criterias) {
        return super.findByCriteria(sessionFactory, criterias);
    }
}
