package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.dao.CarDAO;
import com.ephemeralin.carplace.model.Car;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

/**
 * The type Car service.
 */
@Service("carService")
@Transactional
public class CarService implements IService<Car> {

    @Resource
    private CarDAO carDAO;

    @Override
    public int create(Car c) {
        return this.carDAO.create(c);
    }

    @Override
    public Car findById(int id) {
        return this.carDAO.findById(id);
    }

    @Override
    public List<Car> findAll() {
        return this.carDAO.findAll();
    }

    @Override
    public Car update(Car c) {
        return this.carDAO.update(c);
    }

    @Override
    public boolean delete(int id) {
        return this.carDAO.delete(id);
    }

    @Override
    public List<Car> findByCriteria(HashMap<String, Object> criterias) {
        return null;
    }
}
