package ru.job4j.condition;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class Point.
*
* @author vpiliugin
* @since 23.04.2017
*/

public class PointTest {
  /**
  * Test 1 of point's condition.
  */
  @Test
  public final void whenXTwoYThreeAOneBOneThenTrue() {
      Point point = new Point(2, 3);
      boolean result = point.is(1, 1);
      boolean expected = true;
      assertThat(result, is(expected));
  }
  /**
  * Test 2 of point's condition.
  */
  @Test
  public final void whenXZeroYOneATwoBTwoThenFalse() {
      Point point = new Point(0, 1);
      boolean result = point.is(2, 2);
      boolean expected = false;
      assertThat(result, is(expected));
  }
  /**
  * Test 3 of point's condition.
  */
  @Test
  public final void whenXTwoYTenAThreeBFourThenTrue() {
      Point point = new Point(2, 10);
      boolean result = point.is(3, 4);
      boolean expected = true;
      assertThat(result, is(expected));
  }

}
