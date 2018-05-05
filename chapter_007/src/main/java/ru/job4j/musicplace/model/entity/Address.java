package ru.job4j.musicplace.model.entity;

/**
 * The type Address.
 */
public class Address implements IEntity {

    /**
     * Id.
     */
    private int id;
    /**
     * Name.
     */
    private String name;
    /**
     * User.
     */
    private User user;

    @Override
    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return this.id;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }
}
