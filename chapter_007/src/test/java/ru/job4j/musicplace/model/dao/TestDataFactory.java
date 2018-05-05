package ru.job4j.musicplace.model.dao;

import ru.job4j.musicplace.model.entity.Address;
import ru.job4j.musicplace.model.entity.Genre;
import ru.job4j.musicplace.model.entity.Role;
import ru.job4j.musicplace.model.entity.User;

/**
 * The type Test data factory.
 */
public class TestDataFactory {

    /**
     * Create test Address.
     *
     * @param name name
     * @param user user
     * @return address address
     */
    public static Address createTestAddress(String name, User user) {
        Address address = new Address();
        address.setName(name);
        address.setUser(user);
        int AddressId = AddressDAO.getInstance().create(address);
        address.setId(AddressId);
        return address;
    }

    /**
     * Create test Role.
     *
     * @param name name
     * @return role role
     */
    public static Role createTestRole(String name) {
        Role role = new Role();
        role.setName(name);
        int roleId = RoleDAO.getInstance().create(role);
        role.setId(roleId);
        return role;
    }

    /**
     * Create test user.
     *
     * @param name     name
     * @param password the password
     * @param role     role
     * @return user user
     */
    public static User createTestUser(String name, String password, Role role) {
        User user = new User();
        user.setName(name);
        user.setRole(role);
        user.setPassword(password);
        int userId = UserDAO.getInstance().create(user);
        user.setId(userId);
        return user;
    }

    /**
     * Create test Genre.
     *
     * @param name name
     * @return genre genre
     */
    public static Genre createTestGenre(String name) {
        Genre genre = new Genre();
        genre.setName(name);
        int roleId = GenreDAO.getInstance().create(genre);
        genre.setId(roleId);
        return genre;
    }
}
