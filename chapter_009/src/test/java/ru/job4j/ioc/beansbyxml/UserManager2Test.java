package ru.job4j.ioc.beansbyxml;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * The type User manager 2 test.
 */
@lombok.extern.log4j.Log4j2
public class UserManager2Test {

    /**
     * When add user to storage then should save it.
     */
    @Test
    public void whenAddUserToStorageThenShouldSaveIt() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-beans-by-xml.xml");
        UserService2 userService = context.getBean("userService2", UserService2.class);
        userService.add(new User2());
    }

    /**
     * When load context should get bean.
     */
    @Test
    public void whenLoadContextShouldGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-beans-by-xml.xml");
        UserService2 userService2 = context.getBean(UserService2.class);
        userService2.add(new User2());
        assertNotNull(userService2);
    }
}
