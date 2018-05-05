package ru.job4j.musicplace.model.dao;

import org.junit.*;
import ru.job4j.musicplace.model.entity.Address;
import ru.job4j.musicplace.model.entity.Genre;
import ru.job4j.musicplace.model.entity.Role;
import ru.job4j.musicplace.model.entity.User;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * The type Main repository test.
 */
public class MainRepositoryTest {
    /**
     * Test addresses list.
     */
    private List<Address> addresses = new ArrayList<>();
    /**
     * Test users list.
     */
    private List<User> users = new ArrayList<>();
    /**
     * Test roles list.
     */
    private List<Role> roles = new ArrayList<>();
    /**
     * Test genres list.
     */
    private List<Genre> genres = new ArrayList<>();

    /**
     * Before class test.
     */
    @BeforeClass
    public static void beforeClassTest() {
        MainRepository.getInstance();
    }

    /**
     * Before test.
     */
    @Before
    public void beforeTest() {
        deleteAllTestData();
        Role role = TestDataFactory.createTestRole("testRole123");
        roles.add(role);
        User user = TestDataFactory.createTestUser("testUser123", "testPassword123", role);
        users.add(user);
        Address address = TestDataFactory.createTestAddress("testAddress123", user);
        addresses.add(address);
    }

    /**
     * After test.
     */
    @After
    public void afterTest() {
        deleteAllTestData();
    }

    /**
     * Delete all test data from DB.
     */
    private void deleteAllTestData() {
        for (Genre genre : genres) {
            GenreDAO.getInstance().delete(genre);
        }
        for (Address address : addresses) {
            AddressDAO.getInstance().delete(address);
        }
        for (User user : users) {
            UserDAO.getInstance().delete(user);
        }
        for (Role role : roles) {
            RoleDAO.getInstance().delete(role);
        }
    }

    /**
     * Gets user data test.
     */
    @Test
    public void getUserDataTest() {
        System.out.println("1");
        User user = users.get(0);
        Genre genre = new Genre();
        genre.setName("testGenre123");
        genres.add(genre);
        genre.setId(GenreDAO.getInstance().create(genre));
        MainRepository.getInstance().addGenreToUser(user, genre);

        HashMap<String, Object> userData = MainRepository.getInstance().getUserData(user);
        Role resultRole = (Role) userData.get("Role");
        User resultUser = (User) userData.get("User");
        Address resultAddress = (Address) userData.get("Address");
        addresses.add(resultAddress);
        List<Genre> resultGenres = (List<Genre>) userData.get("GenresList");
        for (Genre g : resultGenres) {
            genres.add(g);
        }

        assertThat(resultRole.getName(), is("testRole123"));
        assertThat(resultUser.getName(), is("testUser123"));
        assertThat(resultAddress.getName(), is("testAddress123"));
        assertThat(resultGenres.get(0).getName(), is("testGenre123"));
    }

    /**
     * Create user with info test.
     */
    @Test
    public void createUserWithInfoTest() {
        System.out.println("2");
        Genre genre = new Genre();
        genre.setName("testGenre123");
        int genreId = GenreDAO.getInstance().create(genre);
        genre.setId(genreId);
        genres.add(genre);
        User user = MainRepository.getInstance().createUserWithInfo(
                "testtest1", "testadr1", roles.get(0), genres);
        users.add(user);
        addresses.add(user.getAddress());

        assertThat(user.getName(), is("testtest1"));
        assertThat(user.getRole().getName(), is("testRole123"));
        assertThat(user.getAddress().getName(), is("testadr1"));
        assertThat(user.getGenres().get(0).getName(), is("testGenre123"));
    }

    /**
     * Add genre to user test.
     */
    @Test
    public void addGenreToUserTest() {
        System.out.println("3");
        User user = users.get(0);
        Genre genre = new Genre();
        genre.setName("testGenre123");
        genres.add(genre);
        genre.setId(GenreDAO.getInstance().create(genre));
        boolean isAdded = MainRepository.getInstance().addGenreToUser(user, genre);
        assertThat(isAdded, is(true));
    }

    /**
     * Find user by address test.
     */
    @Test
    public void findUserByAddressTest() {
        System.out.println("4");
        int actualUserId = users.get(0).getId();
        int foundUserId = MainRepository.getInstance().findUserByAddress(addresses.get(0)).getId();
        assertThat(foundUserId, not(0));
        assertThat(foundUserId, is(actualUserId));
    }

    /**
     * Find users by role test.
     */
    @Test
    public void findUsersByRoleTest() {
        System.out.println("5");
        Role role = roles.get(0);
        User user = TestDataFactory.createTestUser("testUser456", "testPassword123", role);
        users.add(user);
        List<User> usersByRole = MainRepository.getInstance().findUsersByRole(role);
        assertThat(usersByRole.get(0).getName(), is("testUser123"));
        assertThat(usersByRole.get(1).getName(), is("testUser456"));
    }


    /**
     * Find address by user test.
     */
    @Test
    public void findAddressByUserTest() {
        System.out.println("6");
        Address addressByUser = MainRepository.getInstance().findAddressByUser(users.get(0));
        assertThat(addressByUser.getName(), is("testAddress123"));
    }

    /**
     * Find users by genre test.
     */
    @Test
    public void findUsersByGenreTest() {
        System.out.println("7");
        User firstUser = users.get(0);
        Role role = roles.get(0);
        User secondUser = TestDataFactory.createTestUser("testUser456", "testPassword123", role);
        users.add(secondUser);
        Genre genre = new Genre();
        genre.setName("testGenre123");
        genres.add(genre);
        genre.setId(GenreDAO.getInstance().create(genre));
        MainRepository.getInstance().addGenreToUser(firstUser, genre);
        MainRepository.getInstance().addGenreToUser(secondUser, genre);

        List<User> usersByGenre = MainRepository.getInstance().findUsersByGenre(genre);

        assertThat(usersByGenre.get(0).getName(), is("testUser123"));
        assertThat(usersByGenre.get(1).getName(), is("testUser456"));
    }

    /**
     * Find genres by user test.
     */
    @Test
    public void findGenresByUserTest() {
        System.out.println("8");
        User user = users.get(0);
        Genre genre = new Genre();
        genre.setName("testGenre123");
        genre.setId(GenreDAO.getInstance().create(genre));
        genres.add(genre);
        MainRepository.getInstance().addGenreToUser(user, genre);
        Genre secondGenre = new Genre();
        secondGenre.setName("testGenre456");
        secondGenre.setId(GenreDAO.getInstance().create(secondGenre));
        genres.add(secondGenre);
        MainRepository.getInstance().addGenreToUser(user, secondGenre);

        List<Genre> genresByUser = MainRepository.getInstance().findGenresByUser(user);

        assertThat(genresByUser.get(0).getName(), is("testGenre123"));
        assertThat(genresByUser.get(1).getName(), is("testGenre456"));
    }

    /**
     * Create entity from result set test.
     *
     * @throws Exception the exception
     */
    @Test
    public void createEntityFromResultSetTest() throws Exception {
        System.out.println("9");
        final ResultSet resultSet = mock(ResultSet.class);
        when(resultSet.getInt("id")).thenReturn(1);
        when(resultSet.getString("name")).thenReturn("test");

        User user = (User) MainRepository.getInstance().createEntityFromResultSet(resultSet, User.class.getName());
        users.add(user);

        assertThat(user.getId(), is(1));
        assertThat(user.getName(), is("test"));
    }

    /**
     * Is identified test.
     */
    @Test
    public void isIdentifiedTest() {
        System.out.println("10");
        boolean isOk = MainRepository.getInstance().isIdentified("testUser123", "testPassword123");
        assertThat(isOk, is(true));
    }
}