package ru.job4j.ioc.beansbyannotations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * The type User service.
 */
@Repository
@lombok.extern.log4j.Log4j2
public class UserService {

    @Autowired
    private IRepository repository;

    /**
     * Add.
     *
     * @param user the user
     */
    public void add(User user) {
        this.repository.add(user);
    }
}
