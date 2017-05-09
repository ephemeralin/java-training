package ru.job4j.test001;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class Test001.
*
* @author vpiliugin
* @since 0.1
*/

public class Test001Test {
  /**
  * Test 1 of method contains.
  */
  @Test
  public void whenStringContainsSubStringThenTrue() {
    String data1 = "The best java course ever!";
    String data2 = "java";
    boolean result = Test001.contains(data1, data2);
    boolean expected = true;
    assertThat(result, is(expected));
  }

  /**
  * Test 2 of method contains.
  */
  @Test
  public void whenStringDoesNotContainsSubStringThenTrue() {
    String data1 = "In god we trust!";
    String data2 = "hell";
    boolean result = Test001.contains(data1, data2);
    boolean expected = false;
    assertThat(result, is(expected));
  }
}
