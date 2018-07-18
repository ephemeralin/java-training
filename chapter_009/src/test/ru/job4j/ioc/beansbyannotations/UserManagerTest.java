package ru.job4j.ioc.beansbyannotations;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotNull;

public class UserManagerTest {

    @Test
    public void whenAddUserToStorageThenShouldSaveIt() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-beans-by-annotations.xml");
        UserService userService = context.getBean("userService", UserService.class);
        userService.add(new User());
    }

    @Test
    public void whenLoadContextShouldGetBean() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context-beans-by-annotations.xml");
        UserService userService = context.getBean(UserService.class);
        userService.add(new User());
        assertNotNull(userService);
    }
}
