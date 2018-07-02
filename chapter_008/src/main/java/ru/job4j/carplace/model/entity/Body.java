package ru.job4j.carplace.model.entity;

import lombok.Data;

import javax.persistence.*;

/**
 * The type Body.
 */
@Data
@Entity
@Table(name = "bodies", schema = "public")
public class Body {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

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
     * Instantiates a new Body.
     *
     * @param id   the id
     * @param name the name
     */
    public Body(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
