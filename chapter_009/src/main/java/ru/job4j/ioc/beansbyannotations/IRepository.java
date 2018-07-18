package ru.job4j.ioc.beansbyannotations;

import org.springframework.stereotype.Component;

/**
 * The interface Repository.
 */
@Component
public interface IRepository {
    /**
     * Add.
     *
     * @param user the user
     */
    void add(User user);
}
