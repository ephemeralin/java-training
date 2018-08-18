package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.Make;
import com.ephemeralin.carplace.repository.MakeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The Make service.
 */
@Repository("makeService")
@Transactional
public class MakeService implements IMakeService {

    /**
     * The Repository.
     */
    @Autowired
    private MakeRepository repository;

    @Override
    public int create(Make m) {
        return this.repository.save(m).getId();
    }

    @Override
    public Make findById(int id) {
        return this.repository.findById(id).get();
    }

    @Override
    public List<Make> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Make update(Make c) {
        return this.repository.save(c);
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

}
