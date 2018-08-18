package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Model;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Model repository.
 */
@Repository
public interface ModelRepository extends JpaRepository<Model, Integer> {
}
