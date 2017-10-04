package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * The type User store test.
 */
public class UserStoreTest {
    /**
     * When add user then find by id it.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddThenFindByIdIt() throws Exception {
        GeneralStore<User> userStore = new GeneralStore<User>(3);
        userStore.add(new User("a1", "manager"));
        User user = (User) userStore.findById("a1");
        assertThat(user.getId(), is("a1"));
    }

    /**
     * When update then find by id it with other name.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenUpdateThenFindByIdItWithOtherName() throws Exception {
        GeneralStore<User> userStore = new GeneralStore<User>(3);
        userStore.add(new User("a1", "manager"));
        User user = (User) userStore.findById("a1");
        user.setName("director");
        userStore.update(user);
        assertThat(userStore.findById("a1").getName(), is("director"));
    }

    /**
     * When add and delete one then has null.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddAndDeleteOneThenHasNull() throws Exception {
        GeneralStore<User> userStore = new GeneralStore<User>(3);
        userStore.add(new User("a1", "manager"));
        userStore.delete("a1");
        assertNull(userStore.findById("a1"));
    }
}