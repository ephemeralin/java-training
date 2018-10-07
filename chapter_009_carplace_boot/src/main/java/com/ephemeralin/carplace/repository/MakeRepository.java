package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Make;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Make repository.
 */
@Repository
public interface MakeRepository extends JpaRepository<Make, Integer> {
}
