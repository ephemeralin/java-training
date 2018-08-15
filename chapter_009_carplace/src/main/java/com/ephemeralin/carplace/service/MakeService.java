package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.dao.MakeDAO;
import com.ephemeralin.carplace.model.Make;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

/**
 * The type Make service.
 */
@Service("makeService")
@Transactional
public class MakeService implements IService<Make> {

    @Resource
    private MakeDAO makeDAO;

    @Override
    public int create(Make m) {
        return this.makeDAO.create(m);
    }

    @Override
    public Make findById(int id) {
        return this.makeDAO.findById(id);
    }

    @Override
    public List<Make> findAll() {
        return this.makeDAO.findAll();
    }

    @Override
    public Make update(Make c) {
        return this.makeDAO.update(c);
    }

    @Override
    public boolean delete(int id) {
        return this.makeDAO.delete(id);
    }

    @Override
    public List<Make> findByCriteria(HashMap<String, Object> criterias) {
        return null;
    }
}
