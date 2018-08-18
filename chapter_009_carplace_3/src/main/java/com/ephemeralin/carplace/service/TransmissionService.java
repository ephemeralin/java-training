package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.Transmission;
import com.ephemeralin.carplace.repository.TransmissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

/**
 * The Transmission service.
 */
@Repository("transmissionService")
@Transactional
public class TransmissionService implements ITransmissionService {

    /**
     * The Repository.
     */
    @Autowired
    private TransmissionRepository repository;

    @Override
    public int create(Transmission m) {
        return this.repository.save(m).getId();
    }

    @Override
    public Transmission findById(int id) {
        return this.repository.findById(id).get();
    }

    @Override
    public List<Transmission> findAll() {
        return this.repository.findAll();
    }

    @Override
    public Transmission update(Transmission c) {
        return this.repository.save(c);
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

}
