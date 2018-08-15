package com.ephemeralin.carplace.dao;

import com.ephemeralin.carplace.model.Make;
import com.ephemeralin.carplace.model.Model;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


import java.util.HashMap;
import java.util.List;

/**
 * The type Model dao.
 */
@Repository
@Log4j2
public class ModelDAO extends DAO<Model> implements IDAO<Model> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public int create(Model entity) {
        getCurrentSession().save(entity);
        return entity.getId();
    }

    @Override
    public Model findById(int id) {
        Query query = getCurrentSession()
                .createQuery(
                        "FROM Model m " +
                                "JOIN FETCH m.make mk " +
                                "WHERE mk.id = :id");
        query.setParameter("id", id);
        return (Model) query.getSingleResult();
    }

    @Override
    public List findAll() {
        return getCurrentSession().createQuery("FROM Model ml JOIN FETCH ml.make").list();
    }

    @Override
    public Model update(Model entity) {
        Model entityUpdate = getCurrentSession().load(Model.class, entity.getId());
        entityUpdate.setName(entity.getName());
        return entityUpdate;
    }

    @Override
    public boolean delete(int id) {
        Model entity = findById(id);
        return super.delete(sessionFactory, entity);
    }

    /**
     * Find by criteria list.
     *
     * @param criterias the criterias
     * @return the list
     */
    public List findByCriteria(HashMap<String, Object> criterias) {
        if (criterias.containsKey("findAllWithMake")) {
            return findAllWithMake((Make) criterias.get("make"));
        } else {
            return super.findByCriteria(sessionFactory, criterias);
        }
    }

    /**
     * Find all with make list.
     *
     * @param make the make
     * @return the list
     */
    public List findAllWithMake(Make make) {
        Query query = getCurrentSession()
                .createQuery(
                        "FROM Model md " +
                                "JOIN FETCH md.make mk " +
                                "WHERE md.make = :make");
        query.setParameter("make", make);
        return query.list();
    }
}
