package ru.job4j.carplace.model.entity;

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

    @Override
    public String toString() {
        return name;
    }
}
