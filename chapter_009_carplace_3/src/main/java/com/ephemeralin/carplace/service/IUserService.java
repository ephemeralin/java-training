package com.ephemeralin.carplace.service;

import com.ephemeralin.carplace.model.User;

/**
 * The interface Car service.
 */
public interface IUserService extends IService<User> {
    /**
     * Find by login user.
     *
     * @param login the login
     * @return the user
     */
    User findByLogin(String login);
}
