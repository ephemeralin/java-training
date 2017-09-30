package ru.job4j.generics;

/**
 * The type User store.
 */
public class RoleStore extends AbstractStore {

    /**
     * Instantiates a new Role store.
     *
     * @param count the count
     */
    public RoleStore(int count) {
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