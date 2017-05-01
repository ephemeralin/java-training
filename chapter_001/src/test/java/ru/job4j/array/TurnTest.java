package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class Paint.
*
* @author vpiliugin
* @since 0.1
*/

public class TurnTest {
  /**
  * Test 1 of method back.
  */
  @Test
  public void whenTurnArrayWithEvenAmountOfElementsThenTurnedArray() {
    int[] data = new int[] {2, 6, 1, 4};
    int[] result = Turn.back(data);
    int[] expected = new int[] {4, 1, 6, 2};
    assertThat(result, is(expected));
  }

  /**
  * Test 2 of method back.
  */
  @Test
  public void whenTurnArrayWithOddAmountOfElementsThenTurnedArray() {
    int[] data = new int[] {1, 2, 3, 4, 5};
    int[] result = Turn.back(data);
    int[] expected = new int[] {5, 4, 3, 2, 1};
    assertThat(result, is(expected));
  }
}
