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
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authorities_id_seq")
    @SequenceGenerator(
            name = "authorities_id_seq",
            sequenceName = "authorities_id_seq",
            allocationSize = 20
    )
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
