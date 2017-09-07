package ru.job4j.iteratorevennumbers;

import org.junit.Test;

import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Even numbers iterator test.
 */
public class EvenNumbersIteratorTest {

    /**
     * Has next.
     *
     * @throws Exception the exception
     */
    @Test
    public void hasNext() throws Exception {
        Iterator it = new EvenNumbersIterator(new int[] {3, 2, 1, 1});
        assertThat(it.hasNext(), is(true));
    }

    /**
     * Next.
     *
     * @throws Exception the exception
     */
    @Test
    public void next() throws Exception {
        Iterator it = new EvenNumbersIterator(new int[] {2, 5, 1, 4, 1});
        it.next();
        int next = (Integer) it.next();
        assertThat(next, is(4));
    }

}