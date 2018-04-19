package ru.job4j.users.model;

import java.util.Objects;

/**
 * The type Country.
 */
public class Country {
    /**
     * ID.
     */
    private int id;
    /**
     * Name.
     */
    private String name;

    /**
     * Instantiates a new Country.
     *
     * @param id   the id
     * @param name the name
     */
    public Country(int id, String name) {
        this.id = id;
        this.name = name;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Country country = (Country) o;
        return getId() == country.getId() &&
                Objects.equals(getName(), country.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        if (name == null) {
            return "";
        } else {
            return name;
        }
    }
}
