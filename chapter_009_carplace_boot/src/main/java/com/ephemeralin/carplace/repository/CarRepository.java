package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Car;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * The interface Car repository.
 */
@Repository
public interface CarRepository extends JpaRepository<Car, Integer> {
    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    @EntityGraph(attributePaths = {"make", "model", "body", "engine", "transmission", "owner"})
    Optional<Car> findById(int id);

    /**
     * Find all by order by date desc list.
     *
     * @return the list
     */
    @EntityGraph(attributePaths = {"make", "model", "body", "engine", "transmission", "owner"})
    List<Car> findAllByOrderByDateDesc();

    /**
     * Find with photo list.
     *
     * @return the list
     */
    @EntityGraph(attributePaths = {"make", "model", "body", "engine", "transmission", "owner"})
    @Query(value = "FROM Car c WHERE c.base64imageFile != ''", nativeQuery = false)
    List<Car> findWithPhoto();

    /**
     * Find today cars list.
     *
     * @param startDate the start date
     * @param endDate   the end date
     * @return the list
     */
    @EntityGraph(attributePaths = {"make", "model", "body", "engine", "transmission", "owner"})
    @Query(value = "FROM Car c WHERE c.date BETWEEN :startDate AND :endDate", nativeQuery = false)
    List<Car> findTodayCars(@Param ("startDate") Long startDate, @Param ("endDate")Long endDate);
}
