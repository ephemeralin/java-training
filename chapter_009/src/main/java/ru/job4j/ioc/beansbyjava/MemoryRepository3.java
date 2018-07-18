package ru.job4j.ioc.beansbyjava;

/**
 * The type Memory repository 3.
 */
@lombok.extern.log4j.Log4j2
public class MemoryRepository3 implements IRepository3 {
    @Override
    public void add(User3 user) {
        System.out.println("Stored to the memory");
    }
}
