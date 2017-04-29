package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class Max.
*
* @author vpiliugin
* @since 29.04.2017
*/

public class CounterTest {
  /**
  * Test 1 of method add.
  */
  @Test
  public final void whenFirstOneSecondTwoThenTwo() {
      int result = Counter.add(1, 2);
      int expected = 2;
      assertThat(result, is(expected));
  }

  /**
  * Test 2 of method add.
  */
  @Test
  public final void whenFirstOneSecondFiveThenSix() {
      int result = Counter.add(1, 5);
      int expected = 6;
      assertThat(result, is(expected));
  }

  /**
  * Test 3 of method add.
  */
  @Test
  public final void whenFirst4Second10Then28() {
      int result = Counter.add(4, 10);
      int expected = 28;
      assertThat(result, is(expected));
  }

}
