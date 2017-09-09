package ru.job4j.doubleiterator;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Converter iterator test.
 */
public class ConverterIteratorTest {
    /**
     * When it has two inner it.
     */
    @Test
    public void whenItHasTwoInnerIt() {
        Iterator<Iterator<Integer>> it = Arrays.asList(
                Collections.singletonList(1).iterator(),
                Collections.singletonList(2).iterator()
        ).iterator();
        Iterator<Integer> convert = new ConverterIterator().convert(it);
        convert.next();
        int result = convert.next();
        assertThat(result, is(2));
    }

}