package ru.job4j.carplace.model.dao;

import ru.job4j.carplace.model.entity.Car;

import java.sql.SQLException;
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
}
