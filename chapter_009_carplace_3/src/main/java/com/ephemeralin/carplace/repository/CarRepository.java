package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Car;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Car repository.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
}
