package com.ephemeralin.carplace.model;

import lombok.Data;

import javax.persistence.*;

/**
 * The type Transmission.
 */
@Data
@Entity
@Table(name = "transmissions", schema = "public")
public class Transmission {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transmissions_id_seq")
    @SequenceGenerator(
            name = "transmissions_id_seq",
            sequenceName = "transmissions_id_seq",
            allocationSize = 20
    )
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

    @Override
    public String toString() {
        return name;
    }
}
