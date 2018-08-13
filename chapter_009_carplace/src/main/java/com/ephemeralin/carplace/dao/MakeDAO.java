package com.ephemeralin.carplace.dao;

import com.ephemeralin.carplace.model.Make;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The type Make dao.
 */
@Repository
@Log4j2
public class MakeDAO extends DAO<Make> implements IDAO<Make> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int create(Make entity) {
        sessionFactory.openSession().save(entity);
        return entity.getId();
    }

    @Override
    public Make findById(int id) {
        return sessionFactory.openSession().get(Make.class, id);
    }

    @Override
    public List findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM Make ").list();
    }

    @Override
    public Make update(Make entity) {
        Session session = sessionFactory.openSession();
        Make entityUpdate = session.load(Make.class, entity.getId());
        entityUpdate.setName(entity.getName());
        return entityUpdate;
    }

    @Override
    public boolean delete(int id) {
        Make entity = findById(id);
        return super.delete(sessionFactory, entity);
    }
}
