package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.Transmission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


/**
 * The interface Transmission repository.
 */
@Repository
public interface TransmissionRepository extends JpaRepository<Transmission, Integer> {
}
