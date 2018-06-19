package ru.job4j.carplace.model.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

/**
 * The type Body.
 */
@Entity
@Table(name = "bodies", schema = "public")
public class Body {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "car")
    private Set<Car> cars;

    /**
     * Instantiates a new Body.
     */
    public Body() {
    }

    /**
     * Instantiates a new Body.
     *
     * @param id the id
     */
    public Body(int id) {
        this.id = id;
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
        Body that = (Body) o;
        return getId() == that.getId() &&
                Objects.equals(getName(), that.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName());
    }

    @Override
    public String toString() {
        return "Body{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
