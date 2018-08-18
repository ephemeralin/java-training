package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Body;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Body repository.
 */
@Repository
public interface BodyRepository extends JpaRepository<Body, Integer> {

}
