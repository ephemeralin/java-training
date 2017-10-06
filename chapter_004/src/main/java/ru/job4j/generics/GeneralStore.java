package ru.job4j.generics;

/**
 * The type Abstract store new.
 *
 * @param <E> the type parameter
 */
public class GeneralStore<E extends Base> implements Store<E> {

    /**
     * Users.
     */
    private SimpleArray<E> users;

    /**
     * Instantiates a new Abstract store.
     *
     * @param count the count
     */
    GeneralStore(int count) {
        this.users = new SimpleArray<>(count);
    }

    @Override
    public E add(E model) {
        users.add(model);
        return model;
    }

    @Override
    public E update(E model) {
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
            E user = this.users.get(i);
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
