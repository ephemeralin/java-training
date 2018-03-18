package ru.job4j.users.model;

/**
 * The type User.
 */
public class User {
    /**
     * The Name.
     */
    private String name;
    /**
     * The Login.
     */
    private String login;
    /**
     * The Email.
     */
    private String email;
    /**
     * The Created date.
     */
    private Long created;

    /**
     * Instantiates a new User.
     *
     * @param name    the name
     * @param login   the login
     * @param email   the email
     * @param created the created
     */
    public User(String name, String login, String email, Long created) {
        this.email = email;
        this.name = name;
        this.login = login;
        this.created = created;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
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
     * @param login the login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets created.
     *
     * @return the created
     */
    public Long getCreated() {
        return created;
    }

    /**
     * Sets created.
     *
     * @param created the created
     */
    public void setCreated(Long created) {
        this.created = created;
    }

    @Override
    public String toString() {
        return "USER {"
                + "name='" + name + '\''
                + ", login='" + login + '\''
                + ", email='" + email + '\''
                + ", created=" + created
                + '}';
    }
}
