package ru.job4j.ioc.beansbyjava;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * The type App config.
 */
@Configuration
@ComponentScan
public class AppConfig {

    /**
     * Gets user service.
     *
     * @return the user service
     */
    @Bean(name = "userService")
    public UserService3 getUserService() {
        return new UserService3();

    }

    /**
     * Gets repository.
     *
     * @return the repository
     */
    @Bean(name = "userRepository")
    public IRepository3 getRepository() {
        return new MemoryRepository3();
    }
}