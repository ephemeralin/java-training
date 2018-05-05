package ru.job4j.musicplace.model.dao;

import ru.job4j.musicplace.model.entity.Genre;
import ru.job4j.musicplace.model.entity.IEntity;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * The type Genre dao.
 */
public class GenreDAO extends BaseDAO implements IModelDAO<Genre> {

    /**
     * Main repository instance.
     */
    private static GenreDAO instance;

    /**
     * Default constructor.
     */
    private GenreDAO() {
        super();
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized GenreDAO getInstance() {
        if (instance == null) {
            instance = new GenreDAO();
        }
        return instance;
    }

    @Override
    public int create(Genre genre) {
        return super.create(genre, "genres");
    }

    @Override
    public Genre findById(int id) {
        return (Genre) super.findById(id, Genre.class.getName(), "genres");
    }

    @Override
    public Genre findByName(String name) {
        return (Genre) super.findByName(name, Genre.class.getName(), "genres");
    }

    @Override
    public List<? extends IEntity> findAll() {
        return super.findAll(Genre.class.getName(), "genres");
    }

    @Override
    public boolean update(Genre genre) {
        return super.update(genre, "genres");
    }

    @Override
    public boolean delete(Genre genre) {
        boolean result = deleteAllJunctionsForGenre(genre);
        if (result) {
            result = super.delete(genre, "genres");
        }
        return result;
    }

    /**
     * Delete all junctions for genre boolean.
     *
     * @param genre the genre
     * @return the boolean
     */
    private boolean deleteAllJunctionsForGenre(Genre genre) {
        boolean isDeleted = false;
        if (genre != null) {
            String sql = "DELETE FROM user_genre WHERE genre_id = ?";
            Connection conn = null;
            PreparedStatement pstmt = null;
            try {
                conn = getDatabaseConnector().getConnection();
                pstmt = conn.prepareStatement(sql);
                pstmt.setInt(1, genre.getId());
                pstmt.execute();
                getLog().info(String.format("All junctions for genre %s is deleted", genre.getName()));
                isDeleted = true;
            } catch (SQLException e) {
                getLog().error(String.format("SQL Error deleting all junctions for genre %s", genre.getName()), e);
            } finally {
                getDatabaseConnector().closeSqlResources(conn, pstmt, null);
            }
        }
        return isDeleted;
    }
}
