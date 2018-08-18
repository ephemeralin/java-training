package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.Car;

import java.util.List;

/**
 * The interface Car service.
 */
public interface ICarService extends IService<Car> {
    /**
     * Find with photo list.
     *
     * @return the list
     */
    List<Car> findWithPhoto();

    /**
     * Find today cars list.
     *
     * @return the list
     */
    List<Car> findTodayCars();
}
