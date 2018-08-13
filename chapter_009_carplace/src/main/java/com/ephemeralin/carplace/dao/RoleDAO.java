package com.ephemeralin.carplace.dao;

import com.ephemeralin.carplace.model.Role;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The type Role dao.
 */
@Repository
@Log4j2
public class RoleDAO extends DAO<Role> implements IDAO<Role> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int create(Role entity) {
        sessionFactory.openSession().save(entity);
        return entity.getId();
    }

    @Override
    public Role findById(int id) {
        return sessionFactory.openSession().get(Role.class, id);
    }

    @Override
    public List findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM Role ").list();
    }

    @Override
    public Role update(Role entity) {
        Session session = sessionFactory.openSession();
        Role entityUpdate = session.load(Role.class, entity.getId());
        entityUpdate.setName(entity.getName());
        return entityUpdate;
    }

    @Override
    public boolean delete(int id) {
        Role entity = findById(id);
        return super.delete(sessionFactory, entity);
    }
}
