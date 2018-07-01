package ru.job4j.carplace.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Transmission.
 */
@Entity
@Table(name = "transmissions", schema = "public")
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    /**
     * Instantiates a new Transmission.
     */
    public Transmission() {
    }

    /**
     * Instantiates a new Transmission.
     *
     * @param id the id
     */
    public Transmission(int id) {
        this.id = id;
    }


    /**
     * Instantiates a new Transmission.
     *
     * @param id   the id
     * @param name the name
     */
    public Transmission(int id, String name) {
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
        Transmission that = (Transmission) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return name;
    }
}
