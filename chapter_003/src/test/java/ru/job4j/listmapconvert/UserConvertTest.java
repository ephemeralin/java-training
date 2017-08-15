package ru.job4j.listmapconvert;

import org.junit.Test;
import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type User convert test.
 */
public class UserConvertTest {
    /**
     * Process.
     *
     * @throws Exception the exception
     */
    @Test
    public void process() throws Exception {
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1, "Slava", "Saint-Petersburg"));
        list.add(new User(2, "Natasha", "Moscow"));

        String expected = "{1=(id=1, name=Slava, city=Saint-Petersburg), 2=(id=2, name=Natasha, city=Moscow)}";
        assertThat(new UserConvert().process(list).toString(), is(expected));
    }

}