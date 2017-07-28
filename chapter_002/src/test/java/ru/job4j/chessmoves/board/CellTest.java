package ru.job4j.chessmoves.board;

import org.junit.Test;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ephemeralin on 15.07.17.
 */
public class CellTest {

    /**
     * Instance of the Board.
     */
    private final Board board = Board.getInstance();

    /**
     * Gets horizontal distance to.
     *
     * @throws Exception the exception
     */
    @Test
    public void getHorizontalDistanceTo() throws Exception {
        Cell firstCell = board.findCell(2, 'c');
        Cell secondCell = board.findCell(3, 'f');
        int distance = firstCell.getHorizontalDistanceTo(secondCell);
        assertThat(distance, is(-3));
    }

    /**
     * Gets vertical distance to.
     *
     * @throws Exception the exception
     */
    @Test
    public void getVerticalDistanceTo() throws Exception {
        Cell firstCell = board.findCell(2, 'c');
        Cell secondCell = board.findCell(5, 'f');
        int distance = firstCell.getHorizontalDistanceTo(secondCell);
        assertThat(distance, is(-3));
    }

    /**
     * Gets string position.
     *
     * @throws Exception the exception
     */
    @Test
    public void getStringPosition() throws Exception {
        Cell cell = board.findCell(4, 'd');
        assertThat(cell.getStringPosition(), is("d4"));
    }

}