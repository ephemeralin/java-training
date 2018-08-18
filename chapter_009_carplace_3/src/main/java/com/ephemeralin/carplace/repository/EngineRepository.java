package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Engine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Engine repository.
 */
@Repository
public interface EngineRepository extends JpaRepository<Engine, Integer> {
}
