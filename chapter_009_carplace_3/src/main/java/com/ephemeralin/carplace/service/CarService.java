package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.Car;
import com.ephemeralin.carplace.repository.CarRepository;
import com.ephemeralin.utils.DateUtility;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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
        TypedQuery query = em.createQuery(
                "FROM Car c " +
                        "JOIN FETCH c.make mk " +
                        "JOIN FETCH c.model ml " +
                        "JOIN FETCH c.body " +
                        "JOIN FETCH c.engine " +
                        "JOIN FETCH c.transmission " +
                        "JOIN FETCH c.owner " +
                        "WHERE c.id = :id",
                Car.class);
        query.setParameter("id", id);
        return (Car) query.getSingleResult();
    }

    @Override
    public List<Car> findAll() {
        TypedQuery query = em.createQuery(
                "FROM Car c " +
                        "JOIN FETCH c.make mk " +
                        "JOIN FETCH c.model ml " +
                        "JOIN FETCH c.body " +
                        "JOIN FETCH c.engine " +
                        "JOIN FETCH c.transmission " +
                        "JOIN FETCH c.owner " +
                        "ORDER BY c.date DESC ",
                Car.class);
        return query.getResultList();
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
        TypedQuery query = em.createQuery(
                "FROM Car c " +
                        "JOIN FETCH c.make mk " +
                        "JOIN FETCH c.model ml " +
                        "JOIN FETCH c.body " +
                        "JOIN FETCH c.engine " +
                        "JOIN FETCH c.transmission " +
                        "JOIN FETCH c.owner " +
                        "WHERE c.base64imageFile != NULL AND c.base64imageFile != ''",
                Car.class);
        return query.getResultList();
    }

    @Override
    public List<Car> findTodayCars() {
        TypedQuery query = em.createQuery(
                "FROM Car c " +
                        "JOIN FETCH c.make mk " +
                        "JOIN FETCH c.model ml " +
                        "JOIN FETCH c.body " +
                        "JOIN FETCH c.engine " +
                        "JOIN FETCH c.transmission " +
                        "JOIN FETCH c.owner " +
                        "WHERE c.date BETWEEN :startDate AND :endDate",
                Car.class);
        query.setParameter("startDate", DateUtility.getTodayPeriodInMillis().get("startDate"));
        query.setParameter("endDate", DateUtility.getTodayPeriodInMillis().get("endDate"));
        return query.getResultList();
    }
}
