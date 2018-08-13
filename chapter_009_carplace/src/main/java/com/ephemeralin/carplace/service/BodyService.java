package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.dao.BodyDAO;
import com.ephemeralin.carplace.model.Body;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service("bodyService")
@Transactional
public class BodyService implements IService<Body> {

    @Resource
    private BodyDAO bodyDAO;

    @Override
    public int create(Body m) {
        return this.bodyDAO.create(m);
    }

    @Override
    public Body findById(int id) {
        return this.bodyDAO.findById(id);
    }

    @Override
    public List<Body> findAll() {
        return this.bodyDAO.findAll();
    }

    @Override
    public Body update(Body c) {
        return this.bodyDAO.update(c);
    }

    @Override
    public boolean delete(int id) {
        return this.bodyDAO.delete(id);
    }

    @Override
    public List<Body> findByCriteria(HashMap<String, Object> criterias) {
        return null;
    }
}
