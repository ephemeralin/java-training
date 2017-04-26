package ru.job4j.max;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class Max.
*
* @author vpiliugin
* @since 23.04.2017
*/

public class MaxTest {
  /**
  * Test 1 of method max with two parameters.
  */
  @Test
  public final void whenFirstZeroSecondOneThenOne() {
      int result = Max.max(0, 1);
      int expected = 1;
      assertThat(result, is(expected));
  }

  /**
  * Test 2 of method max with two parameters.
  */
  @Test
  public final void whenFirstOneSecondZeroThenOne() {
      int result = Max.max(1, 0);
      int expected = 1;
      assertThat(result, is(expected));
  }

    /**
    * Test 3 of method max with two parameters.
    */
    @Test
    public final void whenFirstOneSecondOneThenOne() {
        int result = Max.max(1, 1);
        int expected = 1;
        assertThat(result, is(expected));
    }


    /**
    * Test 4 of method max with three parameters.
    */
    @Test
    public final void whenFirstOneSecondTwoThirdThreeThenThree() {
        int result = Max.max(1, 2, 3);
        int expected = 3;
        assertThat(result, is(expected));
    }

    /**
    * Test 5 of method max with three parameters.
    */
    @Test
    public final void whenFirstFiveSecondZeroThirdFiveThenFive() {
        int result = Max.max(5, 0, 5);
        int expected = 5;
        assertThat(result, is(expected));
    }

}
