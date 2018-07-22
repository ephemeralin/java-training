package ru.job4j.ioc.repos;

import lombok.Data;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 * The type User service.
 */
@Component
@Log4j2
@Data
public class UserService implements DAO {

    @Autowired
    @Qualifier("jdbcRepository")
    private DAO repository;

    @Override
    public int create(User user) {
        return repository.create(user);
    }

    @Override
    public User findById(int id) {
        return repository.findById(id);
    }

    @Override
    public User update(User user) {
        return repository.update(user);
    }

    @Override
    public boolean delete(User user) {
        return repository.delete(user);
    }
}
