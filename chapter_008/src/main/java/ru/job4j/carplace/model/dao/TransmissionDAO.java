package ru.job4j.carplace.model.dao;

import ru.job4j.carplace.model.entity.Transmission;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Transmission dao.
 */
@lombok.extern.log4j.Log4j2
public class TransmissionDAO extends DAO implements IModelDAO<Transmission> {
    /**
     * Item DAO instance.
     */
    private static TransmissionDAO instance;

    /**
     * Default constructor.
     */
    private TransmissionDAO() {
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

    @Override
    public int create(Transmission transmission) throws SQLException {
        return super.tx(
                session -> {
                    session.save(transmission);
                    log.info("Successfully created " + transmission.toString());
                    return transmission.getId();
                }
        );
    }

    @Override
    public Transmission findById(int id) {
        return super.tx(
                session -> session.get(Transmission.class, id)
        );
    }

    @Override
    public List findAll() {
        return super.tx(
                session -> session.createQuery("FROM Transmission ").list()
        );
    }

    @Override
    public Transmission update(Transmission transmission) {
        return super.tx(
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
        return super.tx(
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
