package ru.job4j.chessmoves2;

import org.junit.Test;

import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * Created by ephemeralin on 04.08.17.
 */
public class BishopTest {
    /**
     * Way.
     *
     * @throws Exception the exception
     */
    @Test
    public void way() throws Exception {
        Board board = new Board();
        Cell currentCell = new Cell(3, 1);
        Figure figure = new Bishop(currentCell);
        board.set(figure);
        Cell[] cells = figure.way(new Cell(5, 3));
        String result = "";
        for (Cell cell : cells) {
            String p = cell.toString();
            result = (result == "") ? p : String.format("%s, %s", result, p);
        }
        assertThat(result, is("x4y2, x5y3"));
    }

}