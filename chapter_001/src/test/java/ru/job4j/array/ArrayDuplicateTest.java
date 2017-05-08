package ru.job4j.array;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class ArrayDuplicate.
*
* @author vpiliugin
* @since 0.1
*/

public class ArrayDuplicateTest {
  /**
  * Test 1 of method remove.
  */
  @Test
  public void whenRemoveDuplicatesThenArrayWithoutDuplicate() {
    String[] data = {"Hello", "World", "Hello", "Love", "World", "Hello", "Super", "Deep", "Love"};
    String[] result = ArrayDuplicate.remove(data);
    String[] expected = {"Hello", "World", "Love", "Super", "Deep"};
    assertThat(result, is(expected));
  }
}
