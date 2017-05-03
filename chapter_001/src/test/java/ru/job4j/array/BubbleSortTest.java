package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class BubbleSort.
*
* @author vpiliugin
* @since 0.1
*/

public class BubbleSortTest {
  /**
  * Test 1 of method sort.
  */
  @Test
  public void whenSortArrayWithThreeElementsThenSortedArray() {
    int[] data = new int[] {3, 1, 2};
    int[] result = BubbleSort.sort(data);
    int[] expected = new int[] {1, 2, 3};
    assertThat(result, is(expected));
    //напишите здесь тест, проверяющий сортировку массива из 10 элементов методом пузырька, например {1, 5, 4, 2, 3, 1, 7, 8, 0, 5}.
  }

  /**
  * Test 2 of method sort.
  */
  @Test
  public void whenSortArrayWithTenElementsThenSortedArray() {
    int[] data = new int[] {1, 9, 4, 2, 0, 8, 7, 6, 2, 5};
    int[] result = BubbleSort.sort(data);
    int[] expected = new int[] {0, 1, 2, 2, 4, 5, 6, 7, 8, 9};
    assertThat(result, is(expected));
  }
}
