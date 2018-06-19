package ru.job4j.carplace.model.dao;

import ru.job4j.todolist.model.entity.Item;

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
     * Find all list.
     *
     * @return the list
     */
    List<Item> findAll();

    /**
     * Update boolean.
     *
     * @param entity the entity
     * @return the Item
     */
    Item update(T entity);

    /**
     * Delete boolean.
     *
     * @param id the entity's id
     * @return result
     */
    boolean delete(int id);
}
