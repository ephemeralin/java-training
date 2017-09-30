package ru.job4j.generics;

/**
 * The type Abstract store.
 */
public abstract class AbstractStore implements Store<Base> {

    /**
     * Users.
     */
    private SimpleArray<Base> users;

    /**
     * Instantiates a new Abstract store.
     *
     * @param count the count
     */
    AbstractStore(int count) {
        this.users = new SimpleArray<>(count);
    }

    @Override
    public Base add(Base model) {
        users.add(model);
        return model;
    }

    @Override
    public Base update(Base model) {
        for (int i = 0; i <= this.users.getLastIndex(); i++) {
            if (this.users.get(i).equals(model)) {
                this.users.update(model, i);
                return model;
            }
        }
        return null;
    }

    @Override
    public boolean delete(String id) {
        for (int i = 0; i <= this.users.getLastIndex(); i++) {
            if (this.users.get(i).getId() == id) {
                this.users.delete(i);
                return true;
            }
        }
        return false;
    }

    /**
     * Find by id base.
     *
     * @param id the id
     * @return the base
     */
    public Base findById(String id) {
        for (int i = 0; i <= this.users.getLastIndex(); i++) {
            Base user = this.users.get(i);
            if (user == null) {
                continue;
            }
            if (user.getId() == id) {
                return user;
            }
        }
        return null;
    }
}
