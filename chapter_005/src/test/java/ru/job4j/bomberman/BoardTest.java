package ru.job4j.bomberman;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Board test.
 */
public class BoardTest {

    /**
     * Generate stones Test.
     */
    @Test
    public void generateStonesTest() {
        Board board = new Board(7, 4, 4);
        try {
            board.generateStones();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        int numberOfStones = board.getStones().length;

        int expectedNumberOfStones = 4;

        assertThat(numberOfStones, is(expectedNumberOfStones));
    }
}