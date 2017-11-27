package ru.job4j.userstorage;

/**
 * The type User.
 */
public class User {
    /**
     * ID of User.
     */
    private int id;
    /**
     * Amount of User.
     */
    private int amount;

    /**
     * Instantiates a new User.
     *
     * @param id     the id
     * @param amount the amount
     */
    public User(int id, int amount) {
        this.id = id;
        this.amount = amount;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets amount.
     *
     * @return the amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets amount.
     *
     * @param amount the amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }
}
