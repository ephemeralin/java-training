package ru.job4j.generics;

/**
 * The interface Store.
 *
 * @param <T> the type parameter
 */
public interface Store<T extends Base> {
    /**
     * Add t.
     *
     * @param model the model
     * @return the t
     */
    T add(T model);

    /**
     * Update t.
     *
     * @param model the model
     * @return the t
     */
    T update(T model);

    /**
     * Delete boolean.
     *
     * @param id the id
     * @return the boolean
     */
    boolean delete(String id);
}
