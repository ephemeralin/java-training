package com.ephemeralin.carplace.model;

import lombok.Data;
import javax.persistence.*;

/**
 * The type User.
 */
@Data
@Entity
@Table(name = "users", schema = "public")
public class User {
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
    @Column(name = "login")
    private String login;
    /**
     * The user's role.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    /**
     * The users's password.
     */
    @Column(name = "password")
    private String password;

    @Override
    public String toString() {
        return login;
    }
}
