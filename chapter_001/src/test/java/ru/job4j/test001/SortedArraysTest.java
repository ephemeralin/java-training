package ru.job4j.test001;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class Paint.
*
* @author vpiliugin
* @since 0.1
*/

public class SortedArraysTest {
  /**
  * Test 1 of method add.
  */

  @Test
  public void whenAddFirstAndSecondArrayThenResultArray1() {
    int[] first = new int[] {1, 2, 4, 6};
    int[] second = new int[] {3, 3, 10};
    int[] result = SortedArrays.add(first, second);
    int[] expected = new int[] {1, 2, 3, 3, 4, 6, 10};
    assertThat(result, is(expected));
    //assertThat(true, is(true));
  }
}
