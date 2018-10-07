package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    /**
     * Find user by username.
     *
     * @param userName the user name
     * @return the optional
     */
    Optional<User> findByUsername(String userName);

}
