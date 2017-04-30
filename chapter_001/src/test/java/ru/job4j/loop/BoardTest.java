package ru.job4j.loop;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
* Test of class Board.
*
* @author vpiliugin
* @since 30.04.2017
*/

public class BoardTest {
  /**
  * Test 1 of method paint.
  */
  @Test
  public final void whenPaintBoardWithWidthThreeAndHeightThreeThenStringWithThreeColsAndThreeRows() {
    Board board = new Board();
    String result = board.paint(3, 3);
    final String s = System.getProperty("line.separator");
    String expected = String.format("x x%s x %sx x%s", s, s, s);
    assertThat(result, is(expected));
 }

 /**
 * Test 2 of method paint.
 */
 @Test
 public final void whenPaintBoardWithWidthFiveAndHeightFourThenStringWithFiveColsAndFourRows() {
   Board board = new Board();
   String result = board.paint(5, 4);
   final String s = System.getProperty("line.separator");
   String expected = String.format("x x x%s x x %sx x x%s x x %s", s, s, s, s);
   assertThat(result, is(expected));
 }
}
