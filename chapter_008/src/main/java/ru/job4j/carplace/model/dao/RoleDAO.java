package ru.job4j.carplace.model.dao;

import ru.job4j.carplace.model.entity.Role;

import java.sql.SQLException;
import java.util.List;

/**
 * The type Role dao.
 */
@lombok.extern.log4j.Log4j2
public class RoleDAO extends DAO implements IModelDAO<Role> {
    /**
     * Item DAO instance.
     */
    private static RoleDAO instance;

    /**
     * Default constructor.
     */
    private RoleDAO() {
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
                session -> session.get(Role.class, id)
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
