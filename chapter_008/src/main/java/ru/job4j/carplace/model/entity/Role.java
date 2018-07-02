package ru.job4j.carplace.model.entity;


import lombok.*;

import javax.persistence.*;

/**
 * The type Role.
 */
@Data
@Entity
@Table(name = "roles", schema = "public")
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
