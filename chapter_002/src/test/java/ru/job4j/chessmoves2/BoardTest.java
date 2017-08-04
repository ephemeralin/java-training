package ru.job4j.chessmoves2;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ephemeralin on 05.08.17.
 */
public class BoardTest {
    /**
     * Move.
     *
     * @throws Exception the exception
     */
    @Test
    public void move() throws Exception {
        Board board = new Board();
        Cell currentCell = new Cell(3, 1);
        Figure figure = new Bishop(currentCell);
        board.set(figure);
        assertThat(board.move(currentCell, new Cell(5, 3)), is(true));
    }

}