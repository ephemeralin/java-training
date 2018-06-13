package ru.job4j.todolist.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import ru.job4j.todolist.model.entity.Item;
import ru.job4j.utils.HibernateUtility;

import java.util.List;

/**
 * The type Item dao.
 */
public class ItemDAO implements IModelDAO<Item> {
    /**
     * Logger instance.
     */
    private Logger log;
    /**
     * Item DAO instance.
     */
    private static ItemDAO instance;

    /**
     * Default constructor.
     */
    private ItemDAO() {
        this.log = LogManager.getLogger(this.getClass());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized ItemDAO getInstance() {
        if (instance == null) {
            instance = new ItemDAO();
        }
        return instance;
    }

    @Override
    public int create(Item item) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        session.close();
        log.info("Successfully created " + item.toString());
        return item.getId();
    }

    @Override
    public Item findById(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        Item item = session.get(Item.class, id);
        session.close();
        return item;
    }

    @Override
    public List<Item> findAll() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        List<Item> items = session.createQuery("FROM Item").list();
        session.close();
        return items;
    }

    /**
     * Find only active list.
     *
     * @return the list
     */
    public List<Item> findOnlyActive() {
        Session session = HibernateUtility.getSessionFactory().openSession();
        List<Item> items = session.createQuery("FROM Item WHERE done = false ").list();
        session.close();
        return items;
    }

    @Override
    public void update(Item item) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        Item itemUpdate = session.load(Item.class, item.getId());
        itemUpdate.setDescription(item.getDescription());
        itemUpdate.setDone(item.isDone());
        session.getTransaction().commit();
        session.close();
        log.info("Successfully updated " + item.toString());
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtility.getSessionFactory().openSession();
        session.beginTransaction();
        Item item = findById(id);
        if (item != null) {
            session.delete(item);
            log.info("Successfully deleted " + item.toString());
        }
        session.getTransaction().commit();
        session.close();
    }
}
