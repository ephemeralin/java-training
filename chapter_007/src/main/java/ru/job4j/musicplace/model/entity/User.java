package ru.job4j.musicplace.model.entity;

import java.util.List;

/**
 * The type User.
 */
public class User implements IEntity {
    /**
     * The user's ID.
     */
    private int id;
    /**
     * The user's name.
     */
    private String name;
    /**
     * The user's role.
     */
    private Role role;
    /**
     * The user's address.
     */
    private Address address;

    /**
     * The users's password.
     */
    private String password;

    /**
     * The user's list of genres.
     */
    private List<Genre> genres;

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets address.
     *
     * @return the address
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets genres.
     *
     * @return the genres
     */
    public List<Genre> getGenres() {
        return genres;
    }

    /**
     * Sets genres.
     *
     * @param genres the genres
     */
    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
