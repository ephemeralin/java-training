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
public class EngineDAO extends DAO<Engine> implements IDAO<Engine> {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int create(Engine entity) {
        getCurrentSession().save(entity);
        return entity.getId();
    }

    @Override
    public Engine findById(int id) {
        return getCurrentSession().get(Engine.class, id);
    }

    @Override
    public List findAll() {
        return getCurrentSession().createQuery("FROM Engine ").list();
    }

    @Override
    public Engine update(Engine entity) {
        Engine entityUpdate = getCurrentSession().load(Engine.class, entity.getId());
        entityUpdate.setName(entity.getName());
        return entityUpdate;
    }

    @Override
    public boolean delete(int id) {
        Engine entity = findById(id);
        return super.delete(sessionFactory, entity);
    }
}
