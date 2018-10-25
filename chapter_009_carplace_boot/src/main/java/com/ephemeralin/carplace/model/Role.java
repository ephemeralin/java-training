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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "roles_id_seq")
    @SequenceGenerator(
            name = "roles_id_seq",
            sequenceName = "roles_id_seq",
            allocationSize = 20
    )
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
