package ru.job4j.primes;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * The type Primes with iterator test.
 */
public class PrimesWithIteratorTest {

    /**
     * When 23 then is prime.
     *
     * @throws Exception the exception
     */
    @Test
    public void when23ThenIsPrime() throws Exception {
        assertThat(PrimesWithIterator.isPrime(23), is(true));
    }

    /**
     * Has next.
     *
     * @throws Exception the exception
     */
    @Test
    public void hasNext() throws Exception {
        PrimesWithIterator pwi = new PrimesWithIterator(new int[]{4, 5, 6, 9, 11});
        pwi.next();
        assertThat(pwi.hasNext(), is(true));
    }

    /**
     * Next.
     *
     * @throws Exception the exception
     */
    @Test
    public void next() throws Exception {
        PrimesWithIterator pwi = new PrimesWithIterator(new int[]{4, 5, 6, 9, 11});
        pwi.next();
        assertThat(pwi.next(), is(11));
    }



}