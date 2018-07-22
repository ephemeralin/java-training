package ru.job4j.ioc.repos;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * The type User manager test.
 */
public class UserManagerTest {
    private ApplicationContext context;
    private UserService userService;

    /**
     * Before test.
     */
    @Before
    public void beforeTest() {
        context = new ClassPathXmlApplicationContext("spring-context.xml");
        userService = context.getBean("userService", UserService.class);
    }

    /**
     * When load context should get bean.
     */
    @Test
    public void whenLoadContextShouldGetBean() {
        userService.create(new User());
        assertNotNull(userService);
    }

    /**
     * When add user to storage then should save it.
     */
    @Test
    public void whenAddUserToStorageThenShouldSaveIt() {
        int id = userService.create(new User());
        assertThat(id, is(0));
    }

    /**
     * When find by id then should return user.
     */
    @Test
    public void whenFindByIdThenShouldReturnUser() {
        User user = new User();
        user.setId(0);
        user.setName("Test name");
        userService.create(user);

        assertThat(userService.findById(user.getId()).getName(), is("Test name"));
    }

    /**
     * When update name then user has new name.
     */
    @Test
    public void whenUpdateNameThenUserHasNewName() {
        User user = new User();
        user.setId(0);
        user.setName("Test name");
        userService.create(user);

        user.setName("New name");
        userService.update(user);

        assertThat(userService.findById(user.getId()).getName(), is("New name"));
    }

    /**
     * Delete.
     */
    @Test
    public void delete() {
        int id = 0;
        User user = new User();
        user.setId(id);
        user.setName("Test name");
        userService.create(user);

        assertTrue(userService.delete(user));
        assertNull(userService.findById(id));
    }
}
