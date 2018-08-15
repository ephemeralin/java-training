package com.ephemeralin.carplace.config;

import com.ephemeralin.carplace.model.*;
import com.ephemeralin.carplace.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

import static org.hibernate.cfg.Environment.*;

/**
 * The type App config.
 */
@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value = {
        @ComponentScan("com.ephemeralin.carplace.model"),
        @ComponentScan("com.ephemeralin.carplace.dao"),
        @ComponentScan("com.ephemeralin.carplace.service"),
        @ComponentScan("com.ephemeralin.carplace.controller")
})
public class AppConfig {
    @Autowired
    private Environment env;

    /**
     * Gets session factory.
     *
     * @return the session factory
     */
    @Bean
    public LocalSessionFactoryBean getSessionFactory() {
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();

        Properties props = new Properties();
        props.put(DRIVER, env.getProperty("db.driver"));
        props.put(URL, env.getProperty("db.url"));
        props.put(USER, env.getProperty("db.user"));
        props.put(PASS, env.getProperty("db.password"));
        props.put(POOL_SIZE, env.getProperty("db.poolsize"));
        props.put(SHOW_SQL, env.getProperty("hibernate.show_sql"));
        props.put(HBM2DDL_AUTO, env.getProperty("hibernate.hbm2ddl.auto"));
        props.put(DIALECT, env.getProperty("hibernate.dialect"));
//        props.put(CURRENT_SESSION_CONTEXT_CLASS, env.getProperty("hibernate.current_session_context_class"));
        props.put(C3P0_MIN_SIZE,
                env.getProperty("hibernate.c3p0.min_size"));
        props.put(C3P0_MAX_SIZE,
                env.getProperty("hibernate.c3p0.max_size"));
        props.put(C3P0_ACQUIRE_INCREMENT,
                env.getProperty("hibernate.c3p0.acquire_increment"));
        props.put(C3P0_TIMEOUT,
                env.getProperty("hibernate.c3p0.timeout"));
        props.put(C3P0_MAX_STATEMENTS,
                env.getProperty("hibernate.c3p0.max_statements"));
        factoryBean.setHibernateProperties(props);

        factoryBean.setPackagesToScan("com.ephemeralin.carplace.model");

        factoryBean.setAnnotatedClasses(Car.class);
        factoryBean.setAnnotatedClasses(User.class);
        factoryBean.setAnnotatedClasses(Role.class);
        factoryBean.setAnnotatedClasses(Transmission.class);
        factoryBean.setAnnotatedClasses(Body.class);
        factoryBean.setAnnotatedClasses(Engine.class);
        factoryBean.setAnnotatedClasses(Model.class);
        factoryBean.setAnnotatedClasses(Make.class);

        return factoryBean;
    }

    /**
     * Gets transaction manager.
     *
     * @return the transaction manager
     */
    @Bean
    public HibernateTransactionManager getTransactionManager() {
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
