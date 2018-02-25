package ru.job4j.tracker;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Properties;

/**
 * Created by ephemeralin on 21.05.17.
 */
public class TrackerPostgres implements Interacting, DBConnectable {
    /**
     * The constant resourcesPath.
     */
    private final String resourcesPath;
    /**
     * Database connection.
     */
    private Connection connection;
    /**
     * Properties of tracker.
     */
    private Properties properties;
    /**
     * Index of the last Item.
     */
    private int lastItemIndex;

    /**
     * Instantiates a new Tracker postgres.
     *
     * @param resourcesPath the resources path
     */
    public TrackerPostgres(String resourcesPath) {
        this.resourcesPath = resourcesPath;
        try {
            InputStream is = new FileInputStream(new File(this.resourcesPath + "tracker.properties"));
            this.properties = new Properties();
            this.properties.load(is);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Add item.
     *
     * @param item the item
     * @return the item
     */
    public Item add(Item item) {
        String sql = "INSERT INTO items (id, name, descr, created) VALUES (?, ?, ?, ?)";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, item.getId());
            pstmt.setString(2, item.getName());
            pstmt.setString(3, item.getDesc());
            pstmt.setLong(4, item.getCreated());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lastItemIndex++;
        return item;
    }

    /**
     * Update.
     *
     * @param item the item
     */
    public void update(Item item) {
        String sql = "UPDATE items SET name = ?, descr = ?, created = ? WHERE id = ?";
        try {
            PreparedStatement pstmt = connection.prepareStatement(sql);
            pstmt.setString(1, item.getName());
            pstmt.setString(2, item.getDesc());
            pstmt.setLong(3, item.getCreated());
            pstmt.setString(4, item.getId());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete.
     *
     * @param item the item
     */
    public void delete(Item item) {
        String sqlDeleteFromComments = "DELETE FROM comments WHERE item_id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlDeleteFromComments)) {
            pstmt.setString(1, item.getId());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlDeleteFromItems = "DELETE FROM items WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlDeleteFromItems)) {
            pstmt.setString(1, item.getId());
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Delete.
     */
    public void deleteAll() {
        String sqlDeleteFromComments = "DELETE FROM comments";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlDeleteFromComments)) {
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        String sqlDeleteFromItems = "DELETE FROM items";
        try (PreparedStatement pstmt = connection.prepareStatement(sqlDeleteFromItems)) {
            pstmt.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find all items.
     *
     * @return array of all items
     */
    public ArrayList<Item> findAll() {
        String sql = "SELECT * FROM items";
        ArrayList<Item> allItems = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Item item = getItemFromResultSet(rs);
                if (item != null) {
                    allItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return allItems;
    }

    /**
     * Find by name item [].
     *
     * @param name the name
     * @return the item []
     */
    public ArrayList<Item> findByName(String name) {
        ArrayList<Item> foundItems = new ArrayList();
        String sql = "SELECT * FROM items WHERE name = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Item item = getItemFromResultSet(rs);
                if (item != null) {
                    foundItems.add(item);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundItems;
    }

    /**
     * Find by id item.
     *
     * @param id the id
     * @return the item
     */
    public Item findByID(String id) {
        Item foundItem = null;
        String sql = "SELECT * FROM items WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                foundItem = getItemFromResultSet(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foundItem;
    }

    /**
     * Gets next id.
     *
     * @return the next id
     */
    public String getNextID() {
        return "task" + lastItemIndex;
    }

    @Override
    public void initConnection() throws SQLException {
        if (this.connection == null || this.connection.isClosed()) {

            try {
                this.connection = DriverManager.getConnection("jdbc:postgresql://"
                                  + properties.getProperty("db.url"),
                                    properties.getProperty("db.user"),
                                    properties.getProperty("db.password"));
            } catch (SQLException e) {
                System.out.println("Connection Failed to: " + properties.getProperty("db.url"));
                e.printStackTrace();
            }
        }
    }

    @Override
    public void closeConnection() {
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    /**
     * Prepare tracker.
     */
    public void prepare() {
        prepareDbTables();
        setLastItemIndex();
    }

    /**
     * Prepare database tables if needed.
     */
    private void prepareDbTables() {
        try  {
            Statement statement = this.connection.createStatement();
            BufferedReader reader = new BufferedReader(new FileReader(resourcesPath + "init_db.sql"));
            String line;
            String sql = "";
            while ((line = reader.readLine()) != null) {
                sql = sql + line;
            }
            statement.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getting number of rows in table 'items' and set it as last item index.
     */
    private void setLastItemIndex() {
        String sql = "SELECT count(*) AS nextIndex FROM items";
        try (Statement stmt = connection.createStatement()) {
            ResultSet rs = stmt.executeQuery(sql);
            if (rs.next()) {
                this.lastItemIndex = rs.getInt("nextIndex");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Get Item object from the Result set of query.
     * @param rs result set
     * @return item
     */
    private Item getItemFromResultSet(ResultSet rs) {
        Item item = null;
        try {
            String id = rs.getString("id");
            String name = rs.getString("name");
            String descr = rs.getString("descr");
            Long created = rs.getLong("created");
            item = new Item(id, name, descr);
            item.setCreated(created);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }
}
