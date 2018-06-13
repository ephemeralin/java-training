package ru.job4j.todolist;

import org.hibernate.SessionFactory;
import ru.job4j.utils.HibernateUtility;

/**
 * The type Application.
 */
public class Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtility.getSessionFactory();
        System.out.println("Session Factory : " + sessionFactory.hashCode());
        System.out.println("Session Factory 2 : " + sessionFactory.hashCode());
        System.out.println("Session Factory 3 : " + sessionFactory.hashCode());
    }
}
