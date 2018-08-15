package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.dao.EngineDAO;
import com.ephemeralin.carplace.model.Engine;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

/**
 * The type Engine service.
 */
@Service("engineService")
@Transactional
public class EngineService implements IService<Engine> {

    @Resource
    private EngineDAO engineDAO;

    @Override
    public int create(Engine e) {
        return this.engineDAO.create(e);
    }

    @Override
    public Engine findById(int id) {
        return this.engineDAO.findById(id);
    }

    @Override
    public List<Engine> findAll() {
        return this.engineDAO.findAll();
    }

    @Override
    public Engine update(Engine c) {
        return this.engineDAO.update(c);
    }

    @Override
    public boolean delete(int id) {
        return this.engineDAO.delete(id);
    }

    @Override
    public List<Engine> findByCriteria(HashMap<String, Object> criterias) {
        return null;
    }
}
