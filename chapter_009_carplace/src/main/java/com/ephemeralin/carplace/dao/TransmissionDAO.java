package com.ephemeralin.carplace.dao;

import com.ephemeralin.carplace.model.Transmission;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * The type Transmission dao.
 */
@Repository
@Log4j2
public class TransmissionDAO extends DAO<Transmission> implements IDAO<Transmission> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int create(Transmission entity) {
        sessionFactory.openSession().save(entity);
        return entity.getId();
    }

    @Override
    public Transmission findById(int id) {
        return sessionFactory.openSession().get(Transmission.class, id);
    }

    @Override
    public List findAll() {
        Session session = sessionFactory.openSession();
        return session.createQuery("FROM Transmission ").list();
    }

    @Override
    public Transmission update(Transmission entity) {
        Session session = sessionFactory.openSession();
        Transmission entityUpdate = session.load(Transmission.class, entity.getId());
        entityUpdate.setName(entity.getName());
        return entityUpdate;
    }

    @Override
    public boolean delete(int id) {
        Transmission entity = findById(id);
        return super.delete(sessionFactory, entity);
    }

    public List findByCriteria(HashMap<String, Object> criterias) {
        return super.findByCriteria(sessionFactory, criterias);
    }
}
