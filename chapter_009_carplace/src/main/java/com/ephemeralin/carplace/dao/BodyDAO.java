package com.ephemeralin.carplace.dao;

import com.ephemeralin.carplace.model.Body;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * The type Body dao.
 */
@Repository
@Log4j2
public class BodyDAO extends DAO<Body> implements IDAO<Body> {

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public int create(Body entity) {
        getCurrentSession().save(entity);
        return entity.getId();
    }

    @Override
    public Body findById(int id) {
        return getCurrentSession().get(Body.class, id);
    }

    @Override
    public List findAll() {
        return getCurrentSession().createQuery("FROM Body ").list();
    }

    @Override
    public Body update(Body entity) {
        Body entityUpdate = getCurrentSession().load(Body.class, entity.getId());
        entityUpdate.setName(entity.getName());
        return entityUpdate;
    }

    @Override
    public boolean delete(int id) {
        Body entity = findById(id);
        return super.delete(sessionFactory, entity);
    }
}
