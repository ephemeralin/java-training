package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.Make;
import com.ephemeralin.carplace.model.Model;
import com.ephemeralin.carplace.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The type Model service.
 */
@Repository("modelService")
@Transactional
public class ModelService implements IModelService {

    /**
     * The Repository.
     */
    @Autowired
    private ModelRepository repository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public int create(Model m) {
        return this.repository.save(m).getId();
    }

    @Override
    public Model findById(int id) {
        TypedQuery query = em.createQuery(
                "FROM Model m " +
                        "JOIN FETCH m.make mk " +
                        "WHERE mk.id = :id",
                Model.class);
        query.setParameter("id", id);
        return (Model) query.getSingleResult();
    }

    @Override
    public List<Model> findAll() {
        TypedQuery query = em.createQuery(
                "FROM Model ml JOIN FETCH ml.make",
                Model.class);
        return query.getResultList();
    }

    @Override
    public Model update(Model c) {
        return this.repository.save(c);
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Model> findByMake(Make make) {
        TypedQuery query = em.createQuery(
                "FROM Model md " +
                        "JOIN FETCH md.make mk " +
                        "WHERE md.make = :make",
                Model.class);
        query.setParameter("make", make);
        return query.getResultList();
    }
}
