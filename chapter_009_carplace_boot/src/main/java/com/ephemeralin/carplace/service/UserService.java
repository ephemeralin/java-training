package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.User;
import com.ephemeralin.carplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;

/**
 * The User service.
 */
@Repository("userService")
@Transactional
public class UserService implements IUserService {

    /**
     * The Repository.
     */
    @Autowired
    private UserRepository repository;

    @Override
    public int create(User e) {
        return this.repository.save(e).getId();
    }

    @Override
    public User findById(int id) {
        return this.repository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

    @Override
    public List<User> findAll() {
        return this.repository.findAll();
    }

    @Override
    public User update(User c) {
        return this.repository.save(c);
    }

    @Override
    public void delete(int id) {
        this.repository.deleteById(id);
    }

    @Override
    public User findByLogin(String userName) {
        return this.repository.findByUsername(userName).orElseThrow(EntityNotFoundException::new);
    }
}
