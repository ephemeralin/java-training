package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.Car;
import com.ephemeralin.carplace.repository.CarRepository;
import com.ephemeralin.utils.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityNotFoundException;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The Car service.
 */
@Repository("carService")
@Transactional
public class CarService implements ICarService {

    @Autowired
    private CarRepository repository;

    @PersistenceContext
    private EntityManager em;

    @Override
    public int create(Car o) {
        return this.repository.save(o).getId();
    }

    @Override
    public Car findById(int id) {
        return this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<Car> findAll() {
        return this.repository.findAllByOrderByDateDesc();
    }

    @Override
    public Car update(Car o) {
        return this.repository.save(o);
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public List<Car> findWithPhoto() {
        return this.repository.findWithPhoto();
    }

    @Override
    public List<Car> findTodayCars() {
        return this.repository.findTodayCars(
                DateUtility.getTodayPeriodInMillis().get("startDate"),
                DateUtility.getTodayPeriodInMillis().get("endDate"));
    }
}
