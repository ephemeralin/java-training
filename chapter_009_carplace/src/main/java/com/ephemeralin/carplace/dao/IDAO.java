package com.ephemeralin.carplace.dao;

import org.hibernate.Session;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface Model dao.
 *
 * @param <T> the type parameter
 */
public interface IDAO<T> {

    /**
     * Gets current session.
     *
     * @return the current session
     */
    Session getCurrentSession();

    /**
     * Create int.
     *
     * @param entity the entity
     * @return the int
     * @throws SQLException the sql exception
     */
    int create(T entity) throws SQLException;

    /**
     * Find by id t.
     *
     * @param id the id
     * @return the t
     */
    T findById(int id);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<T> findAll();

    /**
     * Update boolean.
     *
     * @param entity the entity
     * @return the Item
     */
    T update(T entity);

    /**
     * Delete boolean.
     *
     * @param id the entity's id
     * @return result boolean
     */
    boolean delete(int id);
}
