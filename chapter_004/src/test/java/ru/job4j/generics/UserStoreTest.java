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
     * When add role then find by id it.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddRoleThenFindByIdIt() throws Exception {
        UserStore userStore = new UserStore(3);
        userStore.add(new Role("a1", "manager"));
        Role role = (Role) userStore.findById("a1");
        assertThat(role.getId(), is("a1"));
    }

    /**
     * When update then find by id it with other name.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenUpdateThenFindByIdItWithOtherName() throws Exception {
        UserStore userStore = new UserStore(3);
        userStore.add(new Role("a1", "manager"));
        Role role = (Role) userStore.findById("a1");
        role.setName("director");
        userStore.update(role);
        assertThat(userStore.findById("a1").getName(), is("director"));
    }

    /**
     * When add and delete one then has null.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddAndDeleteOneThenHasNull() throws Exception {
        UserStore userStore = new UserStore(3);
        userStore.add(new Role("a1", "manager"));
        userStore.delete("a1");
        assertNull(userStore.findById("a1"));
    }
}