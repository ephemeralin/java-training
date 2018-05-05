package ru.job4j.musicplace.model.dao;

import ru.job4j.musicplace.model.entity.IEntity;
import ru.job4j.musicplace.model.entity.Role;

import java.util.List;

/**
 * The type Role dao.
 */
public class RoleDAO extends BaseDAO implements IModelDAO<Role> {
    /**
     * Main repository instance.
     */
    private static RoleDAO instance;

    /**
     * Default constructor.
     */
    private RoleDAO() {
        super();
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
    public int create(Role role) {
        return super.create(role, "roles");
    }

    @Override
    public Role findById(int id) {
        return (Role) super.findById(id, Role.class.getName(), "roles");
    }

    @Override
    public Role findByName(String name) {
        return (Role) super.findByName(name, Role.class.getName(), "roles");
    }

    @Override
    public List<? extends IEntity> findAll() {
        return super.findAll(Role.class.getName(), "roles");
    }

    @Override
    public boolean update(Role role) {
        return super.update(role, "roles");
    }

    @Override
    public boolean delete(Role role) {
        return super.delete(role, "roles");
    }
}
