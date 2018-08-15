package com.ephemeralin.carplace.dao;

import com.ephemeralin.carplace.model.Car;
import lombok.extern.log4j.Log4j2;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * The type Car dao.
 */
@Repository
@Log4j2
public class CarDAO extends DAO<Car> implements IDAO<Car> {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Session getCurrentSession() {
        return sessionFactory.getCurrentSession();
    }

    @Override
    public List findAll() {
        return getCurrentSession()
                .createQuery(
                         "FROM Car c " +
                                 "JOIN FETCH c.make mk " +
                                 "JOIN FETCH c.model ml " +
                                 "JOIN FETCH c.body " +
                                 "JOIN FETCH c.engine " +
                                 "JOIN FETCH c.transmission " +
                                 "JOIN FETCH c.owner " +
                                 "ORDER BY c.date DESC ")
                .list();

    }

    @Override
    public int create(Car car) {
        getCurrentSession().save(car);
        return car.getId();
    }

    @Override
    public Car findById(int id) {
        Query query = getCurrentSession()
                .createQuery(
                        "FROM Car c " +
                                "JOIN FETCH c.make mk " +
                                "JOIN FETCH c.model ml " +
                                "JOIN FETCH c.body " +
                                "JOIN FETCH c.engine " +
                                "JOIN FETCH c.transmission " +
                                "JOIN FETCH c.owner " +
                                "WHERE c.id = :id");
        query.setParameter("id", id);
        return (Car) query.getSingleResult();
    }

    /**
     * Find today list.
     *
     * @return the list
     */
    public List findToday() {
        Query query = getCurrentSession()
                .createQuery(
                        "FROM Car c " +
                                "JOIN FETCH c.make mk " +
                                "JOIN FETCH c.model ml " +
                                "JOIN FETCH c.body " +
                                "JOIN FETCH c.engine " +
                                "JOIN FETCH c.transmission " +
                                "JOIN FETCH c.owner " +
                                "WHERE c.date BETWEEN :startDate AND :endDate");
        query.setParameter("startDate", getTodayPeriodInMillis().get("startDate"));
        query.setParameter("endDate", getTodayPeriodInMillis().get("endDate"));
        return query.list();
    }

    /**
     * Find with photo only list.
     *
     * @return the list
     */
    public List findWithPhotoOnly() {
        Query query = getCurrentSession()
                .createQuery(
                        "FROM Car c " +
                                "JOIN FETCH c.make mk " +
                                "JOIN FETCH c.model ml " +
                                "JOIN FETCH c.body " +
                                "JOIN FETCH c.engine " +
                                "JOIN FETCH c.transmission " +
                                "JOIN FETCH c.owner " +
                                "WHERE c.base64imageFile != NULL AND c.base64imageFile != ''");
        return query.list();
    }

    @Override
    public Car update(Car car) {
        Car carUpdate = getCurrentSession().load(Car.class, car.getId());
        carUpdate.setName(car.getName());
        carUpdate.setSold(car.isSold());
        carUpdate.setMake(car.getMake());
        carUpdate.setModel(car.getModel());
        carUpdate.setEngine(car.getEngine());
        carUpdate.setTransmission(car.getTransmission());
        carUpdate.setBody(car.getBody());
        carUpdate.setDate(car.getDate());
        if (!car.getBase64imageFile().isEmpty()) {
            carUpdate.setImage(car.getImage());
            carUpdate.setBase64imageFile(car.getBase64imageFile());
        }
        return carUpdate;
    }

    @Override
    public boolean delete(int id) {
        Car entity = findById(id);
        return super.delete(sessionFactory, entity);
    }

    /**
     * Gives Today period with start date and end date.
     *
     * @return map with two keys: startDate and endDate
     */
    private HashMap<String, Long> getTodayPeriodInMillis() {
        Instant instant = Instant.now();
        ZoneId zoneId = ZoneId.of("Europe/Moscow");
        ZonedDateTime zdt = ZonedDateTime.ofInstant(instant, zoneId);
        ZonedDateTime zdtStart = zdt.toLocalDate().atStartOfDay(zoneId);
        ZonedDateTime zdtTomorrowStart = zdtStart.plusDays(1);
        HashMap<String, Long> period = new HashMap<>();
        period.put("startDate", zdtStart.toInstant().toEpochMilli());
        period.put("endDate", zdtTomorrowStart.toInstant().toEpochMilli());
        return period;
    }

    /**
     * Find by criteria list.
     *
     * @param criterias the criterias
     * @return the list
     */
    public List findByCriteria(HashMap<String, Object> criterias) {
        if (criterias.containsKey("findWithPhotoOnly")) {
            return findWithPhotoOnly();
        } else if (criterias.containsKey("findToday")) {
            return findToday();
        } else {
            return super.findByCriteria(sessionFactory, criterias);
        }
    }
}
