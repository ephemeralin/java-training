package ru.job4j.condition;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.hamcrest.number.IsCloseTo.closeTo;

/**
* Test of class Triangle.
*
* @author vpiliugin
* @since 23.04.2017
*/
public class TriangleTest {
  /**
  * Test for checking is the Triangle real.
  */
  @Test
  public final void whenPointsLiesOnXThenFalse() {
      Triangle triangle = new Triangle(new Point(0, 1), new Point(0, 3), new Point(0, 5));
      boolean result = triangle.isTrueTriangle();
      boolean expected = false;
      assertThat(result, is(expected));
  }

  /**
  * Test 1 for checking Triangle's area.
  */
  @Test
  public final void whenTriangleFirstThanAreaTrue() {
      Triangle triangle = new Triangle(new Point(2, -3), new Point(1, 1), new Point(-6, 5));
      double result = triangle.area();
      double expected = 12;
      assertThat(result, closeTo(expected, 0.01));
  }

  /**
  * Test 2 for checking Triangle's area.
  */
  @Test
  public final void whenTriangleSecondThanAreaTrue() {
      Triangle triangle = new Triangle(new Point(0, 0), new Point(3, 8), new Point(8, 3));
      double result = triangle.area();
      double expected = 27.5;
      assertThat(result, closeTo(expected, 0.01));
  }

}
