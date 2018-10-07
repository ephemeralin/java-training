package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.Body;
import com.ephemeralin.carplace.repository.BodyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The Body service.
 */
@Repository("bodyService")
@Transactional
public class BodyService implements IBodyService {

    @Autowired
    private BodyRepository repository;

    @Override
    public int create(Body m) {
        return this.repository.save(m).getId();
    }

    @Override
    public Body findById(int id) {
        return this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Body> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Body update(Body c) {
        return this.repository.save(c);
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }
}
