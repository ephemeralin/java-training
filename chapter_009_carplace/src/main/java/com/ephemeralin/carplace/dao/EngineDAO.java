package com.ephemeralin.carplace.dao;

import com.ephemeralin.carplace.model.Engine;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The type Engine dao.
 */
@Repository
@Log4j2
public class EngineDAO implements IDAO<Engine> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(Engine entity) {
        sessionFactory.openSession().save(entity);
        return entity.getId();
    }

    @Override
    public Engine findById(int id) {
        return sessionFactory.openSession().get(Engine.class, id);
    }

    @Override
    public List findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM Engine ").list();
    }

    @Override
    public Engine update(Engine entity) {
        Session session = sessionFactory.openSession();
        Engine entityUpdate = session.load(Engine.class, entity.getId());
        entityUpdate.setName(entity.getName());
        return entityUpdate;
    }

    @Override
    public boolean delete(int id) {
        Session session = sessionFactory.openSession();
        Engine entity = findById(id);
        boolean success = false;
        if (entity != null) {
            session.delete(entity);
            success = true;
        }
        return success;
    }
}
