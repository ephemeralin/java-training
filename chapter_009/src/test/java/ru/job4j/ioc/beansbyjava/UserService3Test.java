package ru.job4j.ioc.beansbyjava;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.junit.Assert.assertNotNull;

/**
 * The type User service 3 test.
 */
public class UserService3Test {
    /**
     * When add user to storage then should save it.
     */
    @Test
    public void whenAddUserToStorageThenShouldSaveIt() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService3 service = context.getBean("userService", UserService3.class);
        service.setUserRepository(context.getBean("userRepository", IRepository3.class));
        service.add(new User3());
    }

    /**
     * When load context should get bean.
     */
    @Test
    public void whenLoadContextShouldGetBean() {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        UserService3 service = context.getBean("userService", UserService3.class);
        service.setUserRepository(context.getBean("userRepository", IRepository3.class));
        service.add(new User3());
        assertNotNull(service);
    }
}