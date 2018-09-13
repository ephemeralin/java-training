package com.ephemeralin.carplace.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The type User.
 */
@Data
@Entity
@Table(name = "users", schema = "public")
public class User implements Serializable {
    /**
     * The user's ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * The user's name.
     */
    @Column(name = "username")
    private String username;

    /**
     * The users's password.
     */
    @Column(name = "password")
    private String password;

    @Override
    public String toString() {
        return username;
    }
}
