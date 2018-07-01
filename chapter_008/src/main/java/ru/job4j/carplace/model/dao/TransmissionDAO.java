package ru.job4j.carplace.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.resource.transaction.spi.TransactionStatus;
import ru.job4j.carplace.model.entity.Transmission;
import ru.job4j.utils.HibernateUtility;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

/**
 * The type Transmission dao.
 */
public class TransmissionDAO implements IModelDAO<Transmission> {
    /**
     * Logger instance.
     */
    private Logger log;
    /**
     * Item DAO instance.
     */
    private static TransmissionDAO instance;

    /**
     * Default constructor.
     */
    private TransmissionDAO() {
        this.log = LogManager.getLogger(this.getClass());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized TransmissionDAO getInstance() {
        if (instance == null) {
            instance = new TransmissionDAO();
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

    @Override
    public int create(Transmission transmission) throws SQLException {
        return this.tx(
                session -> {
                    session.save(transmission);
                    log.info("Successfully created " + transmission.toString());
                    return transmission.getId();
                }
        );
    }

    @Override
    public Transmission findById(int id) {
        return this.tx(
                session -> {
                    return session.get(Transmission.class, id);
                }
        );
    }

    @Override
    public List findAll() {
        return this.tx(
                session -> session.createQuery("FROM Transmission ").list()
        );
    }

    @Override
    public Transmission update(Transmission transmission) {
        return this.tx(
                session -> {
                    Transmission transmissionUpdate = session.load(Transmission.class, transmission.getId());
                    transmissionUpdate.setName(transmission.getName());
                    log.info("Successfully updated " + transmission.toString());
                    return transmissionUpdate;
                }
        );
    }

    @Override
    public boolean delete(int id) {
        return this.tx(
                session -> {
                    boolean success = false;
                    Transmission transmission = findById(id);
                    if (transmission != null) {
                        session.delete(transmission);
                        success = true;
                        log.info("Successfully deleted " + transmission.toString());
                    }
                    return success;
                }
        );
    }
}
