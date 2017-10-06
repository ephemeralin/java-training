package ru.job4j.maps;

import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.AnyOf.anyOf;
import static org.junit.Assert.assertThat;

/**
 * The type Maps test.
 */
public class UserTest {

    /**
     * Date of birth.
     */
    private Calendar dateOfBirth;

    /**
     * Sets date of birth.
     */
    @Before
    public void setDateOfBirth() {
        dateOfBirth = new GregorianCalendar(1985, Calendar.DECEMBER, 8);
    }

    /**
     * Creates test hash map with test data.
     * @param first element.
     * @param second element.
     * @return test hash map with elements.
     */
    private Map<User, String> createTestDataHashMap(User first, User second) {
        Map<User, String> testMap = new HashMap<>();
        testMap.put(first, "first user");
        testMap.put(second, "second user");
        return testMap;
    }

    /**
     * When to string map without overriding of two different objects then has both objects.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenToStringMapOfTwoDiffObjectsWithoutOverridingThenHasBothObjects() throws Exception {
        User first = new User("Tom", 1, dateOfBirth);
        User second = new User("Tom", 1, dateOfBirth);
        Map<User, String> testMap = createTestDataHashMap(first, second);

        String expectedValueFirst = "{User [Tom, 1, 1985-12-08]=first user, User [Tom, 1, 1985-12-08]=second user}";
        String expectedValueSecond = "{User [Tom, 1, 1985-12-08]=second user, User [Tom, 1, 1985-12-08]=first user}";

        assertThat(testMap.toString(), anyOf(is(expectedValueFirst), is(expectedValueSecond)));
    }

    /**
     * When to string map of two different objects with overriding of HashCode Then has both objects.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenToStringMapOfTwoDiffObjectsWithOverridingHashCodeThenHasBothObjects() throws Exception {
        User first = new UserWithOverridingHashcode("Tom", 1, dateOfBirth);
        User second = new UserWithOverridingHashcode("Tom", 1, dateOfBirth);

        Map<User, String> testMap = createTestDataHashMap(first, second);

        String expectedValueFirst = "{User [Tom, 1, 1985-12-08]=first user, User [Tom, 1, 1985-12-08]=second user}";
        String expectedValueSecond = "{User [Tom, 1, 1985-12-08]=second user, User [Tom, 1, 1985-12-08]=first user}";

        assertThat(testMap.toString(), anyOf(is(expectedValueFirst), is(expectedValueSecond)));
    }
}
