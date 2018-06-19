package ru.job4j.todolist.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import ru.job4j.todolist.model.entity.Item;
import ru.job4j.utils.HibernateUtility2;

import java.util.List;
import java.util.function.Function;

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

    /**
     * Wrapper for typical transactions.
     *
     * @param command command
     * @param <T>     T
     * @return result
     */
    private <T> T tx(final Function<Session, T> command) {
        final Session session = HibernateUtility2.INSTANCE.getSessionFactory().openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            tx.commit();
            session.close();
        }
    }

    @Override
    public int create(Item item) {
        return this.tx(
                session -> {
                    session.save(item);
                    log.info("Successfully created " + item.toString());
                    return item.getId();
                }
        );
    }

    @Override
    public boolean delete(int id) {
        return this.tx(
                session -> {
                    boolean success = false;
                    Item item = findById(id);
                    if (item != null) {
                        session.delete(item);
                        success = true;
                        log.info("Successfully deleted " + item.toString());
                    }
                    return success;
                }
        );
    }

    @Override
    public Item findById(int id) {
        return this.tx(
                session -> {
                    return session.get(Item.class, id);
                }
        );
    }

    @Override
    public List findAll() {
        return this.tx(
                session -> session.createQuery("FROM Item").list()
        );
    }

    /**
     * Find only active list.
     *
     * @return the list
     */
    public List findOnlyActive() {
        return this.tx(
                session -> session.createQuery("FROM Item WHERE done = false ").list()
        );
    }

    @Override
    public Item update(Item item) {
        return this.tx(
                session -> {
                    Item itemUpdate = session.load(Item.class, item.getId());
                    itemUpdate.setDescription(item.getDescription());
                    itemUpdate.setDone(item.isDone());
                    log.info("Successfully updated " + item.toString());
                    return itemUpdate;
                }
        );
    }
}
