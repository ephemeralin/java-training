package ru.job4j.carplace.model.dao;

import ru.job4j.carplace.model.entity.Make;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Make dao.
 */
@lombok.extern.log4j.Log4j2
public class MakeDAO extends DAO implements IModelDAO<Make> {
    /**
     * Item DAO instance.
     */
    private static MakeDAO instance;

    /**
     * Default constructor.
     */
    private MakeDAO() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized MakeDAO getInstance() {
        if (instance == null) {
            instance = new MakeDAO();
        }
        return instance;
    }

    @Override
    public int create(Make make) throws SQLException {
        return this.tx(
                session -> {
                    session.save(make);
                    log.info("Successfully created " + make.toString());
                    return make.getId();
                }
        );
    }

    @Override
    public Make findById(int id) {
        return this.tx(
                session -> session.get(Make.class, id)
        );
    }

    @Override
    public List findAll() {
        return this.tx(
                session -> session.createQuery("FROM Make").list()
        );
    }

    @Override
    public Make update(Make make) {
        return this.tx(
                session -> {
                    Make makeUpdate = session.load(Make.class, make.getId());
                    makeUpdate.setName(make.getName());
                    log.info("Successfully updated " + make.toString());
                    return makeUpdate;
                }
        );
    }

    @Override
    public boolean delete(int id) {
        return this.tx(
                session -> {
                    boolean success = false;
                    Make make = findById(id);
                    if (make != null) {
                        session.delete(make);
                        success = true;
                        log.info("Successfully deleted " + make.toString());
                    }
                    return success;
                }
        );
    }
}
