package ru.job4j.carplace.model.dao;

import org.hibernate.query.Query;
import ru.job4j.carplace.model.entity.Car;

import java.sql.SQLException;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.HashMap;
import java.util.List;

/**
 * The type Car dao.
 */
@lombok.extern.log4j.Log4j2
public class CarDAO extends DAO implements IModelDAO<Car> {
    /**
     * Item DAO instance.
     */
    private static CarDAO instance;

    /**
     * Default constructor.
     */
    private CarDAO() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized CarDAO getInstance() {
        if (instance == null) {
            instance = new CarDAO();
        }
        return instance;
    }

    @Override
    public int create(Car car) throws SQLException {
        return this.tx(
                session -> {
                    session.save(car);
                    log.info("Successfully created " + car.toString());
                    return car.getId();
                }
        );
    }

    @Override
    public Car findById(int id) {
        return this.tx(
                session -> session.get(Car.class, id)
        );
    }

    @Override
    public List findAll() {
        return this.tx(
                session -> session.createQuery("FROM Car").list()
        );
    }

    /**
     * Find today list.
     *
     * @return the list
     */
    public List findToday() {
        return this.tx(
                session -> {
                    Query query = session.createQuery("FROM Car where date between :startDate and :endDate");
                    query.setParameter("startDate", getTodayPeriodInMillis().get("startDate"));
                    query.setParameter("endDate", getTodayPeriodInMillis().get("endDate"));
                    return query.list();
                }
        );
    }

    /**
     * Find with photo only list.
     *
     * @return the list
     */
    public List findWithPhotoOnly() {
        return this.tx(
                session -> {
                    Query query = session.createQuery("FROM Car where base64imageFile != null and base64imageFile != ''");
                    return query.list();
                }
        );
    }

    @Override
    public Car update(Car car) {
        return this.tx(
                session -> {
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
                    log.info("Successfully updated " + car.toString());
                    return carUpdate;
                }
        );
    }

    @Override
    public boolean delete(int id) {
        return this.tx(
                session -> {
                    boolean success = false;
                    Car car = findById(id);
                    if (car != null) {
                        session.delete(car);
                        success = true;
                        log.info("Successfully deleted " + car.toString());
                    }
                    return success;
                }
        );
    }

    /**
     * Gives Today period with start date and end date.
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
