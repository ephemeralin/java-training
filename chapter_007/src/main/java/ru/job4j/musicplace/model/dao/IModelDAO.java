package ru.job4j.musicplace.model.dao;

import ru.job4j.musicplace.model.entity.IEntity;

import java.sql.SQLException;
import java.util.List;

/**
 * The interface Model dao.
 *
 * @param <T> the type parameter
 */
public interface IModelDAO<T> {
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
     * Find by name t.
     *
     * @param name the name
     * @return the t
     */
    T findByName(String name);

    /**
     * Find all list.
     *
     * @return the list
     */
    List<? extends IEntity> findAll();

    /**
     * Update boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    boolean update(T entity);

    /**
     * Delete boolean.
     *
     * @param entity the entity
     * @return the boolean
     */
    boolean delete(T entity);
}
