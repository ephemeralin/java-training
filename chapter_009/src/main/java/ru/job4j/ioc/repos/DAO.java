package ru.job4j.ioc.repos;

import org.springframework.stereotype.Component;

/**
 * The interface Repository.
 */
@Component
public interface DAO {
    /**
     * Add.
     *
     * @param user the user
     * @return the int
     */
    int create(User user);

    /**
     * Find by id user.
     *
     * @param id the id
     * @return the user
     */
    User findById(int id);

    /**
     * Update user.
     *
     * @param user the user
     * @return the user
     */
    User update(User user);

    /**
     * Delete boolean.
     *
     * @param user the user
     * @return the boolean
     */
    boolean delete(User user);

}
