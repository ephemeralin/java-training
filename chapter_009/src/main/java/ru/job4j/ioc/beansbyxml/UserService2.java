package ru.job4j.ioc.beansbyxml;

/**
 * The type User service 2.
 */
@lombok.extern.log4j.Log4j2
public class UserService2 {
    private final IRepository2 repository2;

    /**
     * Instantiates a new User service 2.
     *
     * @param repository2 the repository 2
     */
    public UserService2(final IRepository2 repository2) {
        this.repository2 = repository2;
    }

    /**
     * Add.
     *
     * @param user the user
     */
    public void add(User2 user) {
        this.repository2.add(user);
    }
}
