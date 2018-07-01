package ru.job4j.carplace.model.dao;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import ru.job4j.utils.HibernateUtility;

import java.util.function.Function;

/**
 * The type Dao.
 */
public class DAO {
    /**
     * Wrapper for typical transactions.
     *
     * @param <T>     T
     * @param command command
     * @return result t
     */
    public  <T> T tx(final Function<Session, T> command) {
        final Session session = HibernateUtility.getSessionFactory("hibernate.cfg_for_cars.xml").openSession();
        final Transaction tx = session.beginTransaction();
        try {
            return command.apply(session);
        } catch (final Exception e) {
            session.getTransaction().rollback();
            throw e;
        } finally {
            if (tx.getStatus().equals(TransactionStatus.ACTIVE)) {
                tx.commit();
            }
            session.close();
        }
    }
}
