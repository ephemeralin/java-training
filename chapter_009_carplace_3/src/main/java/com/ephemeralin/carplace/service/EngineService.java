package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.Engine;
import com.ephemeralin.carplace.repository.EngineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The Engine service.
 */
@Repository("engineService")
@Transactional
public class EngineService implements IEngineService {

    /**
     * The Repository.
     */
    @Autowired
    private EngineRepository repository;

    @Override
    public int create(Engine e) {
        return this.repository.save(e).getId();
    }

    @Override
    public Engine findById(int id) {
        return this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Engine> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Engine update(Engine c) {
        return this.repository.save(c);
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

}
