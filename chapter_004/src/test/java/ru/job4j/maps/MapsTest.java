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
public class MapsTest {
    /**
     * The Map without overriding.
     */
    private Map<User, String> testMapWithoutOverriding;
    /**
     * Date of birth.
     */
    private Calendar dateOfBirth;

    /**
     * Sets test data for the Map without overriding.
     */
    @Before
    public void setTestDataForMapWithoutOverriding() {
        dateOfBirth = new GregorianCalendar(1985, Calendar.DECEMBER, 8);
        User first = new User("Tom", 1, dateOfBirth);
        User second = new User("Tom", 1, dateOfBirth);

        testMapWithoutOverriding = new HashMap<User, String>();
        testMapWithoutOverriding.put(first, "first user");
        testMapWithoutOverriding.put(second, "second user");
    }

    /**
     * When to string map without overriding of two different objects then has both objects.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenToStringMapWithoutOverridingOfTwoDiffObjectsThenHasBothObjects() throws Exception {
        String expectedValueFirst = "{User [Tom, 1, 1985-12-08]=first user, User [Tom, 1, 1985-12-08]=second user}";
        String expectedValueSecond = "{User [Tom, 1, 1985-12-08]=second user, User [Tom, 1, 1985-12-08]=first user}";
        assertThat(testMapWithoutOverriding.toString(), anyOf(is(expectedValueFirst), is(expectedValueSecond)));
    }
}
