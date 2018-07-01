package ru.job4j.carplace.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type Model.
 */
@Entity
@Table(name = "models", schema = "public")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JoinColumn(name = "make_id", nullable = false)
    private Make make;

    /**
     * Instantiates a new Model.
     */
    public Model() {
    }

    /**
     * Instantiates a new Model.
     *
     * @param id the id
     */
    public Model(int id) {
        this.id = id;
    }


    /**
     * Instantiates a new Model.
     *
     * @param id   the id
     * @param name the name
     */
    public Model(int id, String name) {
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
        Model that = (Model) o;
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
