package ru.job4j.ioc.repos;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * The type User service.
 */
@Component
@Log4j2
@Data
public class UserService {

    private final DAO repository;

    /**
     * Instantiates a new User service.
     *
     * @param repository the repository
     */
    @Autowired
    public UserService(@Qualifier("jdbcRepository") DAO repository) {
        this.repository = repository;
    }

    /**
     * Create int.
     *
     * @param user the user
     * @return the int
     */
    public int create(User user) {
        if (user != null) {
            return repository.create(user);
        } else {
            return 0;
        }
    }

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    public User findById(int id) {
        return repository.findById(id);
    }

    /**
     * Update user.
     *
     * @param user the user
     * @return the user
     */
    public User update(User user) {
        if (user != null) {
            return repository.update(user);
        } else {
            return null;
        }
    }

    /**
     * Delete boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean delete(User user) {
        if (user != null) {
            return repository.delete(user);
        } else {
            return false;
        }
    }
}
