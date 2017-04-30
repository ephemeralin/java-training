package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class Factorial.
*
* @author vpiliugin
* @since 30.04.2017
*/

public class FactorialTest {
  /**
  * Test 1 of method calc.
  */
  @Test
  public final void whenZeroThenOne() {
      int result = Factorial.calc(0);
      int expected = 1;
      assertThat(result, is(expected));
  }

  /**
  * Test 2 of method calc.
  */
  @Test
  public final void when5Then120() {
      int result = Factorial.calc(5);
      int expected = 120;
      assertThat(result, is(expected));
  }
}
