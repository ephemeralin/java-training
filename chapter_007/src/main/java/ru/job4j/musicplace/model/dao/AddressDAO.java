package ru.job4j.musicplace.model.dao;

import ru.job4j.musicplace.model.entity.Address;
import ru.job4j.musicplace.model.entity.IEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * The type Address dao.
 */
public class AddressDAO extends BaseDAO implements IModelDAO<Address> {
    /**
     * Main repository instance.
     */
    private static AddressDAO instance;

    /**
     * Default constructor.
     */
    private AddressDAO() {
        super();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized AddressDAO getInstance() {
        if (instance == null) {
            instance = new AddressDAO();
        }
        return instance;
    }

    @Override
    public int create(Address address) {
        int id = 0;
        String sql = "INSERT INTO addresses (id, name, user_id) VALUES (DEFAULT, ?, ?) RETURNING id;";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = getDatabaseConnector().getConnection();
            pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, address.getName());
            pstmt.setInt(2, address.getUser().getId());
            id = super.create(address, pstmt);
        } catch (SQLException e) {
            getLog().error(String.format("SQL Error to put Address with name %s to the DB", address.getName()), e);
        } finally {
            getDatabaseConnector().closeSqlResources(conn, pstmt, null);
        }
        return id;
    }

    @Override
    public Address findById(int id) {
        return (Address) super.findById(id, Address.class.getName(), "addresses");
    }

    @Override
    public Address findByName(String name) {
        return (Address) super.findByName(name, Address.class.getName(), "addresses");
    }

    @Override
    public List<? extends IEntity> findAll() {
        return super.findAll(Address.class.getName(), "addresses");
    }

    @Override
    public boolean update(Address address) {
        return super.update(address, "addresses");
    }

    @Override
    public boolean delete(Address address) {
        return super.delete(address, "addresses");
    }
}
