package com.ephemeralin.carplace.repository;

import com.ephemeralin.carplace.model.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    /**
     * Find by login optional.
     *
     * @param login the login
     * @return the optional
     */
    @EntityGraph(attributePaths = {"role"})
    Optional<User> findByLogin(String login);
}
