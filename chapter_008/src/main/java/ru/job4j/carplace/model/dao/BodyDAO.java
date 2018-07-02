package ru.job4j.carplace.model.dao;

import ru.job4j.carplace.model.entity.Body;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Body dao.
 */
@lombok.extern.log4j.Log4j2
public class BodyDAO extends DAO implements IModelDAO<Body> {
    /**
     * Item DAO instance.
     */
    private static BodyDAO instance;

    /**
     * Default constructor.
     */
    private BodyDAO() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized BodyDAO getInstance() {
        if (instance == null) {
            instance = new BodyDAO();
        }
        return instance;
    }


    @Override
    public int create(Body body) throws SQLException {
        return super.tx(
                session -> {
                    session.save(body);
                    log.info("Successfully created " + body.toString());
                    return body.getId();
                }
        );
    }

    @Override
    public Body findById(int id) {
        return super.tx(
                session -> {
                    return session.get(Body.class, id);
                }
        );
    }

    @Override
    public List findAll() {
        return super.tx(
                session -> session.createQuery("FROM Body ").list()
        );
    }

    @Override
    public Body update(Body body) {
        return super.tx(
                session -> {
                    Body bodyUpdate = session.load(Body.class, body.getId());
                    bodyUpdate.setName(body.getName());
                    log.info("Successfully updated " + body.toString());
                    return bodyUpdate;
                }
        );
    }

    @Override
    public boolean delete(int id) {
        return super.tx(
                session -> {
                    boolean success = false;
                    Body body = findById(id);
                    if (body != null) {
                        session.delete(body);
                        success = true;
                        log.info("Successfully deleted " + body.toString());
                    }
                    return success;
                }
        );
    }
}
