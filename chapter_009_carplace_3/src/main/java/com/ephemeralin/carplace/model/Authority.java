package com.ephemeralin.carplace.model;

import lombok.Data;

import javax.persistence.*;

/**
 * The Authority.
 */
@Data
@Entity
@Table(name = "authorities", schema = "public")
public class Authority {

    /**
     * The authorities ID.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Username.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "username", nullable = false)
    private User user;

    /**
     * Authority.
     */
    @Column(name = "authority")
    private String authority;

    @Override
    public String toString() {
        return authority;
    }
}
