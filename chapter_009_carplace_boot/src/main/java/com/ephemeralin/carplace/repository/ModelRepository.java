package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Make;
import com.ephemeralin.carplace.model.Model;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


/**
 * The interface Model repository.
 */
@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
    /**
     * Find by id optional.
     *
     * @param id the id
     * @return the optional
     */
    @EntityGraph(attributePaths = {"make"})
    Optional<Model> findById(int id);

    /**
     * Find by make list.
     *
     * @param make the make
     * @return the list
     */
    @EntityGraph(attributePaths = {"make"})
    List<Model> findByMake(Make make);

    /**
     * Find all list.
     *
     * @return the list
     */
    @EntityGraph(attributePaths = {"make"})
    List<Model> findAll();
}
