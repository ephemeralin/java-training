package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class Paint.
*
* @author vpiliugin
* @since 30.04.2017
*/

public class PaintTest {
  /**
  * Test 1 of method paint.
  */
  @Test
  public final void piramidHeight1() {
    Paint paint = new Paint();
    String result = paint.piramid(1);
    final String s = System.getProperty("line.separator");
    String expected = String.format("^%s", s);
    assertThat(result, is(expected));
 }


 /**
 * Test 2 of method paint.
 */

@Test
 public final void piramidHeight3() {
   Paint paint = new Paint();
   String result = paint.piramid(3);
   final String s = System.getProperty("line.separator");
   String expected = String.format("  ^  %s ^^^ %s^^^^^%s", s, s, s);
   assertThat(result, is(expected));
 }

 /**
 * Test 3 of method paint.
 */
 @Test
 public final void piramidHeight5() {
   Paint paint = new Paint();
   String result = paint.piramid(5);
   final String s = System.getProperty("line.separator");
   String expected = String.format("    ^    %s   ^^^   %s  ^^^^^  %s ^^^^^^^ %s^^^^^^^^^%s", s, s, s, s, s);
   assertThat(result, is(expected));
 }

}
