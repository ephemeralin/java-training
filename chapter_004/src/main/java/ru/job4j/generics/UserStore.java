package ru.job4j.generics;

/**
 * The type User store.
 */
public class UserStore extends AbstractStore {

    /**
     * Instantiates a new User store.
     *
     * @param count the count
     */
    public UserStore(int count) {
        super(count);
    }

    /**
     * Add base.
     *
     * @param model the model
     * @return the base
     */
    public Base add(Role model) {
        return (Role) super.add(model);
    }

    /**
     * Update role.
     *
     * @param model the model
     * @return the role
     */
    public Role update(Role model) {
        return (Role) super.update(model);
    }

}
