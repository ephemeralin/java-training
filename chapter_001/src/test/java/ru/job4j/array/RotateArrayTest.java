package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class RotateArray.
*
* @author vpiliugin
* @since 0.1
*/

public class RotateArrayTest {
  /**
  * Test 1 of method rotate.
  */
  @Test
  public void whenRotateTwoRowTwoColArrayThenRotatedArray() {
    int[][] data = {{1, 2}, {3, 4}};
    //1 2   3 1
    //3 4   4 2
    int[][] result = RotateArray.rotate(data);
    int[][] expected = {{3, 1}, {4, 2}};
    assertThat(result, is(expected));
  }

  /**
  * Test 2 of method rotate.
  */
  @Test
  public void whenRotateThreeRowThreeColArrayThenRotatedArray() {
    int[][] data = {{1, 2, 3}, {4, 5, 6}, {7, 8, 9}};
    //1 2 3   7 4 1
    //4 5 6   8 5 2
    //7 8 9   9 6 3
    int[][] result = RotateArray.rotate(data);
    int[][] expected = {{7, 4, 1}, {8, 5, 2}, {9, 6, 3}};
    assertThat(result, is(expected));
  }
}
