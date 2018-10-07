package com.ephemeralin.carplace.model;

import lombok.Data;
import javax.persistence.*;

/**
 * The type Role.
 */
@Data
@Entity
@Table(name = "roles_old", schema = "public")
public class Role {
    /**
     * Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    /**
     * Name.
     */
    @Column(name = "name")
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
