package ru.job4j.directory;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertEquals;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.StringContains.containsString;

/**
 * The type Simple directory test.
 */
public class SimpleDirectoryTest {

    /**
     * When insert two then has two.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenInsertTwoThenHasTwo() throws Exception {
        SimpleDirectory<String, String> sd = new SimpleDirectory<>();

        sd.insert("John", "Doe");
        sd.insert("Barak", "Obama");

        assertThat(sd.toString(), containsString("(Barak, Obama)"));
        assertThat(sd.toString(), containsString("(John, Doe)"));
    }

    /**
     * When insert with null key then get null.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenInsertWithNullKeyThenGetNull() throws Exception {
        SimpleDirectory<String, String> sd = new SimpleDirectory<>();

        sd.insert(null, "Karamba");
        String result = sd.get(null);

        assertNull(result);
    }

    /**
     * When insert two similar then has one.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenInsertTwoSimilarThenHasOne() throws Exception {
        SimpleDirectory<String, String> sd = new SimpleDirectory<>();

        sd.insert("John", "Doe");
        sd.insert("John", "Doe");

        assertThat(sd.toString(), is("(John, Doe)"));
    }

    /**
     * When rich load factor then increase container.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenRichLoadFactorThenIncreaseContainer() throws Exception {
        SimpleDirectory<String, String> sd = new SimpleDirectory<>();

        sd.insert("John", "Doe");
        sd.insert("Barak", "Obama");
        sd.insert("Tom", "Soyer");
        sd.insert("Bruce", "Lee");

        assertEquals(sd.getSize(), 6);
    }


    /**
     * When get by key then has its value.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenGetByKeyThenHasItsValue() throws Exception {
        SimpleDirectory<String, String> sd = new SimpleDirectory<>();

        sd.insert("John", "Doe");
        sd.insert("Barak", "Obama");

        assertThat(sd.get("Barak"), is("Obama"));
    }

    /**
     * When delete one of two then has only one.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenDeleteOneOfTwoThenHasOnlyOne() throws Exception {
        SimpleDirectory<String, String> sd = new SimpleDirectory<>();

        sd.insert("John", "Doe");
        sd.insert("Barak", "Obama");

        sd.delete("John");

        assertThat(sd.toString(), is("(Barak, Obama)"));
    }

    /**
     * When iterate two then has two.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenIterateTwoThenHasTwo() throws Exception {
        SimpleDirectory<String, String> sd = new SimpleDirectory<>();

        sd.insert("John", "Doe");
        sd.insert("Barak", "Obama");

        Iterator it = sd.iterator();
        String result = "";
        while (it.hasNext()) {
            result = String.format("%s, %s", result, it.next());
        }

        assertThat(result, containsString("John"));
        assertThat(result, containsString("Barak"));
    }

}