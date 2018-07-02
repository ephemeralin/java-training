package ru.job4j.carplace.model.dao;

import ru.job4j.carplace.model.entity.Engine;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Engine dao.
 */
@lombok.extern.log4j.Log4j2
public class EngineDAO extends DAO implements IModelDAO<Engine> {
    /**
     * Item DAO instance.
     */
    private static EngineDAO instance;

    /**
     * Default constructor.
     */
    private EngineDAO() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized EngineDAO getInstance() {
        if (instance == null) {
            instance = new EngineDAO();
        }
        return instance;
    }

    @Override
    public int create(Engine engine) throws SQLException {
        return this.tx(
                session -> {
                    session.save(engine);
                    log.info("Successfully created " + engine.toString());
                    return engine.getId();
                }
        );
    }

    @Override
    public Engine findById(int id) {
        return this.tx(
                session -> {
                    return session.get(Engine.class, id);
                }
        );
    }

    @Override
    public List findAll() {
        return this.tx(
                session -> session.createQuery("FROM Engine").list()
        );
    }

    @Override
    public Engine update(Engine engine) {
        return this.tx(
                session -> {
                    Engine engineUpdate = session.load(Engine.class, engine.getId());
                    engineUpdate.setName(engine.getName());
                    log.info("Successfully updated " + engine.toString());
                    return engineUpdate;
                }
        );
    }

    @Override
    public boolean delete(int id) {
        return this.tx(
                session -> {
                    boolean success = false;
                    Engine engine = findById(id);
                    if (engine != null) {
                        session.delete(engine);
                        success = true;
                        log.info("Successfully deleted " + engine.toString());
                    }
                    return success;
                }
        );
    }
}
