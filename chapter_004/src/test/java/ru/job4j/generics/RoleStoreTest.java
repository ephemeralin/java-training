package ru.job4j.generics;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;

/**
 * The type Role store test.
 */
public class RoleStoreTest {

    /**
     * When add role then find by id it.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddRoleThenFindByIdIt() throws Exception {
        RoleStore roleStore = new RoleStore(3);
        roleStore.add(new Role("a1", "manager"));
        Role role = (Role) roleStore.findById("a1");
        assertThat(role.getId(), is("a1"));
    }

    /**
     * When update then find by id it with other name.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenUpdateThenFindByIdItWithOtherName() throws Exception {
        RoleStore roleStore = new RoleStore(3);
        roleStore.add(new Role("a1", "manager"));
        Role role = (Role) roleStore.findById("a1");
        role.setName("director");
        roleStore.update(role);
        assertThat(roleStore.findById("a1").getName(), is("director"));
    }

    /**
     * When add and delete one then has null.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenAddAndDeleteOneThenHasNull() throws Exception {
        RoleStore roleStore = new RoleStore(3);
        roleStore.add(new Role("a1", "manager"));
        roleStore.delete("a1");
        assertNull(roleStore.findById("a1"));
    }
}