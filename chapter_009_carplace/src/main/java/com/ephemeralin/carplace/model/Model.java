package com.ephemeralin.carplace.model;

import lombok.Data;
import javax.persistence.*;

/**
 * The type Model.
 */
@Data
@Entity
@Table(name = "models", schema = "public")
public class Model {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    @Override
    public String toString() {
        return name;
    }
}
