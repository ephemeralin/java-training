package ru.job4j.users.model;

import java.util.Objects;

/**
 * The type City.
 */
public class City {
    /**
     * ID.
     */
    private int id;
    /**
     * Name.
     */
    private String name;
    /**
     * Country reference.
     */
    private Country country;

    /**
     * Instantiates a new City.
     *
     * @param id      the id
     * @param name    the name
     * @param country the country
     */
    public City(int id, String name, Country country) {
        this.id = id;
        this.name = name;
        this.country = country;
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

    /**
     * Gets country.
     *
     * @return the country
     */
    public Country getCountry() {
        return country;
    }

    /**
     * Sets country.
     *
     * @param country the country
     */
    public void setCountry(Country country) {
        this.country = country;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        City city = (City) o;
        return getId() == city.getId() &&
                Objects.equals(getName(), city.getName()) &&
                Objects.equals(getCountry(), city.getCountry());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName(), getCountry());
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
