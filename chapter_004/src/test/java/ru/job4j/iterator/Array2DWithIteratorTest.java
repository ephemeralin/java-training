package ru.job4j.iterator;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ephemeralin on 20.08.17.
 */
public class Array2DWithIteratorTest {

    /**
     * When four next then iterator has not next.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenFourNextThenIteratorHasNotNext() throws Exception {
        Array2DWithIterator it = new Array2DWithIterator(new int[][] {{1, 2}, {3, 4}});

        it.next();
        it.next();
        it.next();
        it.next();
        boolean result = it.hasNext();

        assertThat(result, is(false));
    }

    /**
     * When three next then iterator three.
     *
     * @throws Exception the exception
     */
    @Test
    public void whenThreeNextThenIteratorThree() throws Exception {
        Array2DWithIterator it = new Array2DWithIterator(new int[][] {{1, 2}, {3, 4}});

        it.next();
        it.next();
        int result = (Integer) it.next();

        assertThat(result, is(3));
    }

}