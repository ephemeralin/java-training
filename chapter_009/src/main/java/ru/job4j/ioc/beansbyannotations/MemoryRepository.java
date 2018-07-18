package ru.job4j.ioc.beansbyannotations;

import org.springframework.stereotype.Component;

/**
 * The type Memory repository.
 */
@lombok.extern.log4j.Log4j2
@Component
public class MemoryRepository implements IRepository {
    @Override
    public void add(User user) {
        System.out.println("Stored to the memory");
    }
}
