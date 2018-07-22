package ru.job4j.ioc.repos;

import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

import java.util.HashMap;

/**
 * The type Memory repository.
 */
@Log4j2
@Repository
@Qualifier("memoryRepository")
public class MemoryRepository implements DAO {

    private HashMap<Integer, User> data;
    private int nextId;

    /**
     * Instantiates a new Memory repository.
     */
    public MemoryRepository() {
        System.out.println("Memory storage initiated");
        this.data = new HashMap<>();
        this.nextId = 0;
    }

    @Override
    public int create(User user) {
        user.setId(nextId);
        data.put(nextId, user);
        return nextId++;
    }

    @Override
    public User findById(int id) {
        return data.get(id);
    }

    @Override
    public User update(User newUser) {
        data.put(newUser.getId(), newUser);
        return data.get(newUser.getId());
    }

    @Override
    public boolean delete(User user) {
        return data.remove(user.getId()) != null;
    }
}
