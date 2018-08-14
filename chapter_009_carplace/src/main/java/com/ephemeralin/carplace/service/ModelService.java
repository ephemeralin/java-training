package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.dao.ModelDAO;
import com.ephemeralin.carplace.model.Model;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service("modelService")
@Transactional
public class ModelService implements IService<Model> {

    @Resource
    private ModelDAO modelDAO;

    @Override
    public int create(Model m) {
        return this.modelDAO.create(m);
    }

    @Override
    public Model findById(int id) {
        return this.modelDAO.findById(id);
    }

    @Override
    public List<Model> findAll() {
        return this.modelDAO.findAll();
    }

    @Override
    public Model update(Model c) {
        return this.modelDAO.update(c);
    }

    @Override
    public boolean delete(int id) {
        return this.modelDAO.delete(id);
    }

    @Override
    public List<Model> findByCriteria(HashMap<String, Object> criterias) {
        return this.modelDAO.findByCriteria(criterias);
    }

}
