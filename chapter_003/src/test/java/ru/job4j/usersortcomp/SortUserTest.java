package ru.job4j.usersortcomp;

import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ephemeralin on 16.08.17.
 */
public class SortUserTest {
    /**
     * Sort by name length.
     *
     * @throws Exception the exception
     */
    @Test
    public void sortByNameLength() throws Exception {
        ArrayList<User> list = new ArrayList<User>();
        list.add(new User("jared", 40));
        list.add(new User("richard", 48));
        list.add(new User("mia", 26));

        String expectedListString = "[(mia, 26), (jared, 40), (richard, 48)]";

        assertThat(new SortUser().sortByNameLength(list).toString(), is(expectedListString));
    }

    /**
     * Sort by name than by age.
     *
     * @throws Exception the exception
     */
    @Test
    public void sortByNameThanByAge() throws Exception {
        ArrayList<User> list = new ArrayList<User>();
        list.add(new User("john", 51));
        list.add(new User("jared", 40));
        list.add(new User("john", 33));
        list.add(new User("richard", 48));
        list.add(new User("mia", 26));
        list.add(new User("john", 32));

        String expectedListString = "[(jared, 40), (john, 32), (john, 33), (john, 51), (mia, 26), (richard, 48)]";

        assertThat(new SortUser().sortByNameThanByAge(list).toString(), is(expectedListString));

    }

}