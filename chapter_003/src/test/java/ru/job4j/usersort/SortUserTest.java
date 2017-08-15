package ru.job4j.usersort;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Set;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * The Sort user test.
 */
public class SortUserTest {
    /**
     * Sort.
     *
     * @throws Exception the exception
     */
    @Test
    public void sort() throws Exception {
        List<User> list = new ArrayList<>();
        list.addAll(Arrays.asList(new User("john", 30), new User("sara", 25), new User("mike", 32), new User("gordon", 27)));

        assertThat(new SortUser().sort(list).toString(), is("[(sara, 25), (gordon, 27), (john, 30), (mike, 32)]"));

        SortUser sortUser =  new SortUser();
        Set<User> set = sortUser.sort(list);

        System.out.println(set);
        }
    }
