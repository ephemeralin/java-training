package ru.job4j.chessmoves.board;

import org.junit.Test;
import ru.job4j.chessmoves.figures.Figure;

import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;
import static org.hamcrest.core.Is.is;

/**
 * Created by ephemeralin on 15.07.17.
 */
public class BoardTest {

    /**
     * Instance of the Board.
     */
    private final Board board = Board.getInstance();

    /**
     * Choose figure on cell.
     *
     * @throws Exception the exception
     */
    @Test
    public void chooseFigureOnCell() throws Exception {
        Board board = Board.getInstance();
        board.setup(true);
        Figure figure = board.chooseFigureOnCell(1, 'f');
        assertThat(figure.getFullName(), is("White Bishop on f1"));
    }

    /**
     * Gets instance.
     *
     * @throws Exception the exception
     */
    @Test
    public void getInstance() throws Exception {
        Board firstBoard = Board.getInstance();
        Board secondBoard = Board.getInstance();
        assertTrue(firstBoard == secondBoard);
    }

    /**
     * Gets next column letter when h then 0.
     *
     * @throws Exception the exception
     */
    @Test
    public void getNextColumnLetterWhenHThen0() throws Exception {
        assertThat(board.getNextColumnLetter('h'), is('\0'));
    }

    /**
     * Gets next column letter when d then e.
     *
     * @throws Exception the exception
     */
    @Test
    public void getNextColumnLetterWhenDThenE() throws Exception {
        assertThat(board.getNextColumnLetter('d'), is('e'));
    }

    /**
     * Gets previous column letter when a then 0.
     *
     * @throws Exception the exception
     */
    @Test
    public void getPreviousColumnLetterWhenAThen0() throws Exception {
        assertThat(board.getPreviousColumnLetter('a'), is('\0'));
    }

    /**
     * Gets previous column letter when d then c.
     *
     * @throws Exception the exception
     */
    @Test
    public void getPreviousColumnLetterWhenDThenC() throws Exception {
        assertThat(board.getPreviousColumnLetter('d'), is('c'));
    }

    /**
     * Gets next row number when 2 then 3.
     *
     * @throws Exception the exception
     */
    @Test
    public void getNextRowNumberWhen2Then3() throws Exception {
        assertThat(board.getNextRowNumber(2), is(3));
    }

    /**
     * Gets next row number when 8 then 0.
     *
     * @throws Exception the exception
     */
    @Test
    public void getNextRowNumberWhen8Then0() throws Exception {
        assertThat(board.getNextRowNumber(8), is(0));
    }

    /**
     * Gets previous row number when 3 then 2.
     *
     * @throws Exception the exception
     */
    @Test
    public void getPreviousRowNumberWhen3Then2() throws Exception {
        assertThat(board.getPreviousRowNumber(3), is(2));
    }

    /**
     * Gets previous row number when 1 then 0.
     *
     * @throws Exception the exception
     */
    @Test
    public void getPreviousRowNumberWhen1Then0() throws Exception {
        assertThat(board.getPreviousRowNumber(1), is(0));
    }

    /**
     * Find cell when row 4 column e then find cell.
     *
     * @throws Exception the exception
     */
    @Test
    public void findCellWhenRow4ColumnEThenFindCell() throws Exception {
        Cell cell = board.findCell(4, 'e');
        assertThat(cell.getRow(), is(4));
        assertThat(cell.getColumn(), is('e'));
    }

    /**
     * Find direction.
     *
     * @throws Exception the exception
     */
    @Test
    public void findDirection() throws Exception {
        Cell firstCell = board.findCell(2, 'c');
        Cell secondCell = board.findCell(3, 'd');
        Direction direction = board.findDirection(firstCell, secondCell);
        assertThat(direction, is(Direction.RIGHT_UP));
    }

}