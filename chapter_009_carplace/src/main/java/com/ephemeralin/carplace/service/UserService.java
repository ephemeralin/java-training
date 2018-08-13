package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.dao.UserDAO;
import com.ephemeralin.carplace.model.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.HashMap;
import java.util.List;

@Service("userService")
@Transactional
public class UserService implements IService<User> {

    @Resource
    private UserDAO userDAO;

    @Override
    public int create(User e) {
        return this.userDAO.create(e);
    }

    @Override
    public User findById(int id) {
        return this.userDAO.findById(id);
    }

    @Override
    public List<User> findAll() {
        return this.userDAO.findAll();
    }

    @Override
    public User update(User c) {
        return this.userDAO.update(c);
    }

    @Override
    public boolean delete(int id) {
        return this.userDAO.delete(id);
    }

    @Override
    public List<User> findByCriteria(HashMap<String, Object> criterias) {
        return this.userDAO.findByCriteria(criterias);

    }
}
