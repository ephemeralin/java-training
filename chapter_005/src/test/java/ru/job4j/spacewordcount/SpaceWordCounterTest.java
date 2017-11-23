package ru.job4j.spacewordcount;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Space word counter test.
 */
public class SpaceWordCounterTest {
    /**
     * When count spaces then has six.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenCountSpacesThenHasSix() throws Exception {
        SpaceWordCounter counter = new SpaceWordCounter("there is six spaces in the sentence");

        assertThat(counter.countSpaces(), is(6));
    }

    /**
     * When count words then has seven.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenCountWordsThenHasSeven() throws Exception {
        SpaceWordCounter counter = new SpaceWordCounter("there is seven words in the sentence");

        assertThat(counter.countWords(), is(7));
    }

}