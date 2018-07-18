package ru.job4j.ioc.beansbyxml;

/**
 * The type Memory repository 2.
 */
@lombok.extern.log4j.Log4j2
public class MemoryRepository2 implements IRepository2 {
    @Override
    public void add(User2 user) {
        System.out.println("Stored to the memory");
    }
}
