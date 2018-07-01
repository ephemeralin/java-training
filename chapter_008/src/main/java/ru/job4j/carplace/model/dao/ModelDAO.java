package ru.job4j.carplace.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.query.Query;
import ru.job4j.carplace.model.entity.Model;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Model dao.
 */
public class ModelDAO extends DAO implements IModelDAO<Model> {
    /**
     * Logger instance.
     */
    private Logger log;
    /**
     * Item DAO instance.
     */
    private static ModelDAO instance;

    /**
     * Default constructor.
     */
    private ModelDAO() {
        this.log = LogManager.getLogger(this.getClass());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized ModelDAO getInstance() {
        if (instance == null) {
            instance = new ModelDAO();
        }
        return instance;
    }

    @Override
    public int create(Model model) throws SQLException {
        return this.tx(
                session -> {
                    session.save(model);
                    log.info("Successfully created " + model.toString());
                    return model.getId();
                }
        );
    }

    @Override
    public Model findById(int id) {
        return this.tx(
                session -> {
                    return session.get(Model.class, id);
                }
        );
    }

    @Override
    public List findAll() {
        return this.tx(
                session -> session.createQuery("FROM Model").list()
        );
    }

    @Override
    public Model update(Model model) {
        return this.tx(
                session -> {
                    Model modelUpdate = session.load(Model.class, model.getId());
                    modelUpdate.setName(model.getName());
                    log.info("Successfully updated " + model.toString());
                    return modelUpdate;
                }
        );
    }

    @Override
    public boolean delete(int id) {
        return this.tx(
                session -> {
                    boolean success = false;
                    Model model = findById(id);
                    if (model != null) {
                        session.delete(model);
                        success = true;
                        log.info("Successfully deleted " + model.toString());
                    }
                    return success;
                }
        );
    }

    /**
     * Gets models by make id.
     *
     * @param makeId the make id
     * @return the models by make id
     */
    public List getModelsByMakeId(int makeId) {
        return this.tx(
                session -> {
                    Query query = session.createQuery("FROM Model WHERE make.id = :make_id");
                    query.setParameter("make_id", makeId);
                    return query.list();
                }
        );
    }
}
