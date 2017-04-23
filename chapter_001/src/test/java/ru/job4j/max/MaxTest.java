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
  * Test of class Max.
  */
  @Test
  public final void whenFirstZeroSecondOneThenOne() {
      int result = Max.max(0, 1);
      int expected = 1;
      assertThat(result, is(expected));
  }

  /**
  * Test of class Max.
  */
  @Test
  public final void whenFirstOneSecondZeroThenOne() {
      int result = Max.max(1, 0);
      int expected = 1;
      assertThat(result, is(expected));
  }
  /**
  * Test of class Max.
  */
  @Test
  public final void whenFirstOneSecondOneThenOne() {
      int result = Max.max(1, 1);
      int expected = 1;
      assertThat(result, is(expected));
  }

}
