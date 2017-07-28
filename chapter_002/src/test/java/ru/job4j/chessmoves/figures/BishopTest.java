package ru.job4j.chessmoves.figures;

import org.junit.Test;
import ru.job4j.chessmoves.board.Board;
import ru.job4j.chessmoves.board.Cell;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Test class for Bishop.
 */
public class BishopTest {
    /**
     * Find possible positions.
     *
     * @throws Exception the exception
     */
    @Test
    public void findPossiblePositions() throws Exception {
        Board board = Board.getInstance();
        board.setup(true);
        Figure bishopC1 = board.chooseFigureOnCell(1, 'c');
        Cell[] possibleCells = bishopC1.findPossiblePositions();
        String possiblePositionsString = "";

        for (int i = 0; i < possibleCells.length; i++) {
            Cell cell = possibleCells[i];
            if (cell == null) {
                break;
            }
            String p = cell.getStringPosition();
            possiblePositionsString = (possiblePositionsString == "") ? p : String.format("%s, %s", possiblePositionsString, p);
        }

        assertThat(possiblePositionsString, is("d2, e3, f4, g5, h6, b2, a3"));
    }

    /**
     * Move is possible.
     *
     * @throws Exception the exception
     */
    @Test
    public void moveIsPossible() throws Exception {
        Board board = Board.getInstance();
        board.setup(true);
        Figure bishopC1 = board.chooseFigureOnCell(1, 'c');
        boolean moveIsPossible = bishopC1.moveIsPossible(board.findCell(4, 'f'));
        assertTrue(moveIsPossible);
    }

    /**
     * Move.
     *
     * @throws Exception the exception
     */
    @Test
    public void move() throws Exception {
        Board board = Board.getInstance();
        board.setup(true);
        Figure bishopC1 = board.chooseFigureOnCell(1, 'c');
        Cell[] wayCells = bishopC1.move(board.findCell(6, 'h'));
        String wayString = "";

        for (int i = 0; i < wayCells.length; i++) {
            Cell cell = wayCells[i];
            if (cell == null) {
                break;
            }
            String p = cell.getStringPosition();
            wayString = (wayString == "") ? p : String.format("%s, %s", wayString, p);
        }

        assertThat(wayString, is("d2, e3, f4, g5, h6"));
    }

}