package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.Make;
import com.ephemeralin.carplace.model.Model;
import com.ephemeralin.carplace.repository.ModelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The type Model service.
 */
@Repository("modelService")
@Transactional
public class ModelService implements IModelService {

    /**
     * Model repository.
     */
    @Autowired
    private ModelRepository repository;

    @Override
    public int create(Model m) {
        return this.repository.save(m).getId();
    }

    @Override
    public Model findById(int id) {
        return this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Model> findAll() {
        return this.repository.findAll();
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
        return this.repository.findByMake(make);
    }
}
