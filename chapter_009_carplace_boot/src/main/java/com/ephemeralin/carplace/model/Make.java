package com.ephemeralin.carplace.model;

import lombok.Data;

import javax.persistence.*;

/**
 * The type Make.
 */
@Data
@Entity
@Table(name = "makes", schema = "public")
public class Make {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "makes_id_seq")
    @SequenceGenerator(
            name = "makes_id_seq",
            sequenceName = "makes_id_seq",
            allocationSize = 20
    )
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    /**
     * Instantiates a new Make.
     */
    public Make() {
    }

    /**
     * Instantiates a new Make.
     *
     * @param id the id
     */
    public Make(int id) {
        this.id = id;
    }


    /**
     * Instantiates a new Make.
     *
     * @param id   the id
     * @param name the name
     */
    public Make(int id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
