package ru.job4j.carplace.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.carplace.model.entity.Role;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Role dao.
 */
public class RoleDAO extends DAO implements IModelDAO<Role> {
    /**
     * Logger instance.
     */
    private Logger log;
    /**
     * Item DAO instance.
     */
    private static RoleDAO instance;

    /**
     * Default constructor.
     */
    private RoleDAO() {
        this.log = LogManager.getLogger(this.getClass());
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized RoleDAO getInstance() {
        if (instance == null) {
            instance = new RoleDAO();
        }
        return instance;
    }

    @Override
    public int create(Role role) throws SQLException {
        return super.tx(
                session -> {
                    session.save(role);
                    log.info("Successfully created " + role.toString());
                    return role.getId();
                }
        );
    }

    @Override
    public Role findById(int id) {
        return super.tx(
                session -> {
                    return session.get(Role.class, id);
                }
        );
    }

    @Override
    public List findAll() {
        return this.tx(
                session -> session.createQuery("FROM Role ").list()
        );
    }

    @Override
    public Role update(Role role) {
        return this.tx(
                session -> {
                    Role roleUpdate = session.load(Role.class, role.getId());
                    roleUpdate.setName(role.getName());
                    log.info("Successfully updated " + role.toString());
                    return roleUpdate;
                }
        );
    }

    @Override
    public boolean delete(int id) {
        return this.tx(
                session -> {
                    boolean success = false;
                    Role role = findById(id);
                    if (role != null) {
                        session.delete(role);
                        success = true;
                        log.info("Successfully deleted " + role.toString());
                    }
                    return success;
                }
        );
    }
}
