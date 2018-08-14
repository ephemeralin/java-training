package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.dao.TransmissionDAO;
import com.ephemeralin.carplace.model.Transmission;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service("transmissionService")
@Transactional
public class TransmissionService implements IService<Transmission> {

    @Resource
    private TransmissionDAO transmissionDAO;

    @Override
    public int create(Transmission m) {
        return this.transmissionDAO.create(m);
    }

    @Override
    public Transmission findById(int id) {
        return this.transmissionDAO.findById(id);
    }

    @Override
    public List<Transmission> findAll() {
        return this.transmissionDAO.findAll();
    }

    @Override
    public Transmission update(Transmission c) {
        return this.transmissionDAO.update(c);
    }

    @Override
    public boolean delete(int id) {
        return this.transmissionDAO.delete(id);
    }

    @Override
    public List<Transmission> findByCriteria(HashMap<String, Object> criterias) {
        return this.transmissionDAO.findByCriteria(criterias);
    }

}
