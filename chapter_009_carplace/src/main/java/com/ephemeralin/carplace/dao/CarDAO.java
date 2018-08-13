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
    public int create(Car car) {
        sessionFactory.openSession().save(car);
        return car.getId();
    }

    @Override
    public Car findById(int id) {
        return sessionFactory.openSession().get(Car.class, id);
    }

    @Override
    public List findAll() {
//        return getCurrentSession().createQuery("FROM Car c order by c.date desc ").list();
        return sessionFactory.openSession().createQuery("FROM Car c order by c.date desc ").list();
    }

    /**
     * Find today list.
     *
     * @return the list
     */
    public List findToday() {
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Car where date between :startDate and :endDate");
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
        Session session = sessionFactory.openSession();
        Query query = session.createQuery("FROM Car where base64imageFile != null and base64imageFile != ''");
        return query.list();
    }

    @Override
    public Car update(Car car) {
        Session session = sessionFactory.openSession();
        Car carUpdate = session.load(Car.class, car.getId());
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
}
