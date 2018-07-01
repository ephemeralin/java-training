package ru.job4j.carplace.model.entity;

import javax.persistence.*;
import java.util.Objects;

/**
 * The type User.
 */
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
    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private Role role;
    /**
     * The users's password.
     */
    @Column(name = "password")
    private String password;

    /**
     * Gets role.
     *
     * @return the role
     */
    public Role getRole() {
        return role;
    }

    /**
     * Sets role.
     *
     * @param role the role
     */
    public void setRole(Role role) {
        this.role = role;
    }

    /**
     * Gets login.
     *
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Sets login.
     *
     * @param name the name
     */
    public void setLogin(String name) {
        this.login = name;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets password.
     *
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets password.
     *
     * @param password the password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return getId() == user.getId() &&
                Objects.equals(getLogin(), user.getLogin()) &&
                Objects.equals(getRole(), user.getRole()) &&
                Objects.equals(getPassword(), user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getRole(), getPassword());
    }

    @Override
    public String toString() {
        return login;
    }
}
