package ru.job4j.tracker;

import org.junit.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ephemeralin on 21.05.17.
 */
public class TrackerPostgresTest {

    /**
     * Tracker test instance.
     */
    private TrackerPostgres tracker;

    /**
     * Prepare test environment.
     */
    @BeforeClass
    public static void prepareTestEnvironment() {
        createTestDatabase();
    }

    /**
     * Prepare test tracker.
     */
    @Before
    public void prepareTestTracker() {
        String resourcesPath = "resources/tracker_test/";
        this.tracker = new TrackerPostgres(resourcesPath);
        try {
            this.tracker.initConnection();
            this.tracker.prepare();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Create test database.
     */
    public static void createTestDatabase() {
        String script = "CREATE DATABASE tracker_test"
                + "    WITH "
                + "    OWNER = postgres"
                + "    ENCODING = 'UTF8'"
                + "    CONNECTION LIMIT = -1;";

        try {
            executeScript(script);
            System.out.println("Test database created SUCCESSFULLY.");
        } catch (SQLException e) {
            System.out.println("Test database create FAILED.");
            e.printStackTrace();
        }
    }

    /**
     * Drop test database.
     */
    public static void dropTestDatabase() {
        String script = "DROP DATABASE tracker_test;";
        try {
            executeScript(script);
            System.out.println("Test database drop SUCCESS.");
        } catch (SQLException e) {
            System.out.println("Test database drop FAILED.");
            e.printStackTrace();
        }
    }

    /**
     * Execute script.
     *
     * @param script the script
     * @throws SQLException the sql exception
     */
    public static void executeScript(String script) throws SQLException {
        Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://localhost:5432/", "postgres",
                "password");
        Statement statement = connection.createStatement();
        statement.executeUpdate(script);
        if (connection != null) {
            connection.close();
        }
    }

    /**
     * When add new item then tracker has same item.
     * Test for methods: add, findAll
     */
    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        Item item = new Item("test1", "test item 1", "test description");
        tracker.add(item);
        assertThat(tracker.findAll().get(0).getName(), is("test item 1"));
    }

    /**
     * When delete first item then tracker does not have any items.
     * Test for methods: add, delete
     */
    @Test
    public void whenDeleteFirstItemThenTrackerDoesNotHaveItems() {
        Item item = new Item("test1", "test item 1", "test description");
        tracker.add(item);
        tracker.delete(item);
        assertThat(tracker.findAll().size(), is(0));
    }

    /**
     * When find by name then tracker returns the item.
     * Test for methods: add, findByName
     */
    @Test
    public void whenFindByNameThenTrackerReturnsItem() {
        Item item = new Item("test1", "test item 1", "test description");
        tracker.add(item);
        assertThat(tracker.findByName("test item 1").get(0).toString(), is(item.toString()));
    }

    /**
     * When find by ID then tracker returns the item.
     * Test for methods: add, findByID
     */
    @Test
    public void whenFindByIDThenTrackerReturnsItem() {
        Item item = new Item("test1", "test item 1", "test description");
        tracker.add(item);
        assertThat(tracker.findByID("test1").toString(), is(item.toString()));
    }

    /**
     * When update Item then item is updated.
     * Test for methods: add, update
     */
    @Test
    public void whenUpdateItemThenTrackerReturnsItem() {
        Item item1 = new Item("test1", "test item 1", "test description 1");
        tracker.add(item1);

        Item item2 = new Item("test2", "test item 2", "test description 2");
        tracker.add(item2);

        Item itemUpdated = new Item("test2", "updated item", "updated description");
        tracker.update(itemUpdated);

        assertThat(tracker.findAll().get(1).getName(), is("updated item"));
    }

    /**
     * Release test environment.
     */
    @After
    public void releaseTestEnvironment() {
        tracker.deleteAll();
        tracker.closeConnection();
    }

    /**
     * Delete test environment.
     */
    @AfterClass
    public static void deleteTestEnvironment() {
        dropTestDatabase();
    }
}

