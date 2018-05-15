package ru.job4j.musicplace.model.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ru.job4j.musicplace.model.entity.IEntity;
import ru.job4j.utils.PostgresConnectorDBCP;
import ru.job4j.utils.PropertiesStorage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Base dao.
 */
public abstract class BaseDAO {
    /**
     * Database connector.
     */
    private final PostgresConnectorDBCP databaseConnector;
    /**
     * Logger instance.
     */
    private Logger log;

    /**
     * Instantiates a new Base dao.
     */
    public BaseDAO() {
        this.log = LogManager.getLogger(this.getClass());
        final PropertiesStorage propertiesStorage = new PropertiesStorage("/properties-music-place.properties");
        this.databaseConnector = new PostgresConnectorDBCP(propertiesStorage);
    }

    /**
     * Gets database connector.
     *
     * @return the database connector
     */
    public PostgresConnectorDBCP getDatabaseConnector() {
        return databaseConnector;
    }

    /**
     * Gets log.
     *
     * @return the log
     */
    public Logger getLog() {
        return log;
    }

    /**
     * Create int.
     *
     * @param entity    the entity
     * @param tableName the table name
     * @return the int
     */
    public int create(IEntity entity, String tableName) {
        int id = 0;
        if (entity != null) {
            String sql = "INSERT INTO tableName (id, name) VALUES (DEFAULT, ?) RETURNING id;";
            sql = sql.replace("tableName", tableName);
            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)
            ) {
                pstmt.setString(1, entity.getName());
                id = create(entity, pstmt);
            } catch (SQLException e) {
                getLog().error(String.format("SQL Error to put entity(%s) with name %s to the DB",
                        tableName, entity.getName()), e);
            }
        }
        return id;
    }

    /**
     * Create int.
     *
     * @param entity the entity
     * @param pstmt  the pstmt
     * @return the int
     */
    public int create(IEntity entity, PreparedStatement pstmt) {
        String entityName = entity.getClass().getName();
        int key = 0;
        ResultSet rs = null;
        try {
            pstmt.executeUpdate();
            rs = pstmt.getGeneratedKeys();
            if (rs != null && rs.next()) {
                key = rs.getInt(1);
            } else {
                getLog().error(String.format("SQL Error to put entity(%s) with name %s to the DB", entityName, entity.getName()));
            }
        } catch (SQLException e) {
            getLog().error(String.format("SQL Error to put entity(%s) with name %s to the DB", entityName, entity.getName()), e);
        }
        return key;
    }

    /**
     * Update boolean.
     *
     * @param entity the entity
     * @param pstmt  the pstmt
     * @return the boolean
     */
    public boolean update(IEntity entity, PreparedStatement pstmt) {
        boolean isUpdated = false;
        String entityName = entity.getClass().getName();
        try {
            pstmt.execute();
            isUpdated = true;
        } catch (SQLException e) {
            getLog().error(String.format("SQL Error while updating entity(%s) with name %s in the DB", entityName, entity.getName()), e);
        }
        return isUpdated;
    }

    /**
     * Update boolean.
     *
     * @param entity    the entity
     * @param tableName the table name
     * @return the boolean
     */
    public boolean update(IEntity entity, String tableName) {
        boolean isUpdated = false;
        if (entity != null) {
            String sql = "UPDATE tableName SET name = ? WHERE id = ?";
            sql = sql.replace("tableName", tableName);
            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setString(1, entity.getName());
                pstmt.setInt(2, entity.getId());
                pstmt.execute();
                isUpdated = true;
            } catch (SQLException e) {
                getLog().error(String.format("SQL Error while updating Entity with name %s to the DB", entity.getName()), e);
            }
        }
        return isUpdated;
    }

    /**
     * Delete boolean.
     *
     * @param entity    the entity
     * @param tableName the table name
     * @return the boolean
     */
    public boolean delete(IEntity entity, String tableName) {
        boolean isDeleted = false;
        if (entity != null) {
            String sql = "DELETE FROM tableName WHERE id = ?";
            sql = sql.replace("tableName", tableName);
            try (Connection conn = databaseConnector.getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)
            ) {
                pstmt.setInt(1, entity.getId());
                pstmt.execute();
                isDeleted = true;
            } catch (SQLException e) {
                getLog().error(String.format("SQL Error deleting Entity with name %s in the DB", entity.getName()), e);
            }
        }
        return isDeleted;
    }

    /**
     * Find by name entity.
     *
     * @param entityName the entity name
     * @param className  the class name
     * @param tableName  the table name
     * @return the entity
     */
    public IEntity findByName(String entityName, String className, String tableName) {
        String sql =
                "SELECT tableName.id AS id, tableName.name AS name " +
                        "FROM tableName AS tableName " +
                        "WHERE tableName.name = ?;";
        sql = sql.replace("tableName", tableName);
        IEntity entity = null;
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setString(1, entityName);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                entity = (IEntity) Class.forName(className).getConstructor().newInstance();
                entity.setId(id);
                entity.setName(name);
            } else {
                getLog().error(String.format("SQL Error to find %s with id %s in DB", className, entityName));
            }
        } catch (SQLException e) {
            getLog().error(String.format("SQL Error to find %s with id %s in DB", className, entityName));
        } catch (Exception e) {
            getLog().error(String.format("Unknown Error to find %s with id %s in DB", className, entityName));
        }
        return entity;
    }

    /**
     * Find by id entity.
     *
     * @param entityId  the entity id
     * @param className the class name
     * @param tableName the table name
     * @return the entity
     */
    public IEntity findById(int entityId, String className, String tableName) {
        String sql =
                "SELECT tableName.id AS id, tableName.name AS name " +
                        "FROM tableName AS tableName " +
                        "WHERE tableName.id = ?;";
        sql = sql.replace("tableName", tableName);
        IEntity entity = null;
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            pstmt.setInt(1, entityId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                entity = createEntityFromResultSet(rs, className);
            } else {
                getLog().error(String.format("SQL Error to find %s with id %s in DB", className, entityId));
            }
        } catch (SQLException e) {
            getLog().error(String.format("SQL Error to find %s with id %s in DB", className, entityId));
        }
        return entity;
    }

    /**
     * Find all list.
     *
     * @param className the class name
     * @param tableName the table name
     * @return the list
     */
    public List<? extends IEntity> findAll(String className, String tableName) {
        ArrayList<IEntity> entityList = new ArrayList<>();
        String sql =
                "SELECT * FROM tableName AS tableName;";
        sql = sql.replace("tableName", tableName);
        IEntity entity = null;
        try (Connection conn = databaseConnector.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)
        ) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                entity = createEntityFromResultSet(rs, className);
                entityList.add(entity);
                getLog().info(String.format("%s with id %s found in DB", className, entity.getId()));
            }
        } catch (SQLException e) {
            getLog().error(String.format("SQL Error to find all %s in DB", className));
        }
        return entityList;
    }

    /**
     * Create Entity from the ResultSet.
     * @param rs ResultSet
     * @param className Name of class
     * @return Entity
     * @throws Exception exception
     */
    private IEntity createEntityFromResultSet(ResultSet rs, String className) {
        IEntity entity = null;
        try {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            entity = (IEntity) Class.forName(className).getConstructor().newInstance();
            entity.setId(id);
            entity.setName(name);
        } catch (Exception e) {
            log.error("Error while creating entity from result set", e);
        }
        return entity;
    }
}
