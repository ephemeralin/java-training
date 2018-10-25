package com.ephemeralin.carplace.model;

import lombok.Data;

import javax.persistence.*;


/**
 * The type Engine.
 */
@Data
@Entity
@Table(name = "engines", schema = "public")
public class Engine {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "engines_id_seq")
    @SequenceGenerator(
            name = "engines_id_seq",
            sequenceName = "engines_id_seq",
            allocationSize = 20
    )
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    /**
     * Instantiates a new Engine.
     */
    public Engine() {
    }

    /**
     * Instantiates a new Engine.
     *
     * @param id the id
     */
    public Engine(int id) {
        this.id = id;
    }


    /**
     * Instantiates a new Engine.
     *
     * @param id   the id
     * @param name the name
     */
    public Engine(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
