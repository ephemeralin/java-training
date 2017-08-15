package ru.job4j.listmapconvert;

/**
 * The type User.
 */
public class User {
    /**
     * ID of user.
     */
    private int id;
    /**
     * Name of user.
     */
    private String name;
    /**
     * City of user.
     */
    private String city;

    /**
     * Instantiates a new User.
     *
     * @param id   the id
     * @param name the name
     * @param city the city
     */
    public User(int id, String name, String city) {
        this.id = id;
        this.name = name;
        this.city = city;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return String.format("(id=%d, name=%s, city=%s)", this.id, this.name, this.city);
    }
}
