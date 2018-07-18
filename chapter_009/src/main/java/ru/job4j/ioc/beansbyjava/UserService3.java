package ru.job4j.ioc.beansbyjava;

import lombok.Data;

/**
 * The type User service 3.
 */
@lombok.extern.log4j.Log4j2
@Data
public class UserService3 {
    private IRepository3 userRepository;

    /**
     * Add.
     *
     * @param user the user
     */
    public void add(User3 user) {
        this.userRepository.add(user);
    }
}
