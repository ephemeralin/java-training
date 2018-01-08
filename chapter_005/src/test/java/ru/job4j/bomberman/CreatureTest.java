package ru.job4j.bomberman;

import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Creature test.
 */
public class CreatureTest {

    /**
     * Gets possible coordinates case one test.
     */
    @Test
    public void getPossibleCoordinatesCaseOneTest() {
        Board board = new Board(3, 5);
        Monster monster = new Monster("Monster", 0, 2, board);
        HashSet<List<Integer>> possibleCoordinates = monster.getPossibleCoordinates();

        HashSet<List<Integer>> expectedPossibleCoordinates = new HashSet<List<Integer>>() {
            {
                add(Arrays.asList(0, 1));
                add(Arrays.asList(0, 3));
                add(Arrays.asList(1, 2));
            }
        };
        assertThat(possibleCoordinates, is(expectedPossibleCoordinates));
    }

    /**
     * Gets possible coordinates case two test.
     */
    @Test
    public void getPossibleCoordinatesCaseTwoTest() {
        Board board = new Board(3, 5);
        Monster monster = new Monster("Monster", 2, 4, board);
        HashSet<List<Integer>> possibleCoordinates = monster.getPossibleCoordinates();

        HashSet<List<Integer>> expectedPossibleCoordinates = new HashSet<List<Integer>>() {
            {
                add(Arrays.asList(2, 3));
                add(Arrays.asList(1, 4));
            }
        };
        assertThat(possibleCoordinates, is(expectedPossibleCoordinates));
    }

    /**
     * Gets possible coordinates case three test.
     */
    @Test
    public void getPossibleCoordinatesCaseThreeTest() {
        Board board = new Board(3, 5);
        Monster monster = new Monster("Monster", 1, 2, board);
        HashSet<List<Integer>> possibleCoordinates = monster.getPossibleCoordinates();

        HashSet<List<Integer>> expectedPossibleCoordinates = new HashSet<List<Integer>>() {
            {
                add(Arrays.asList(1, 1));
                add(Arrays.asList(2, 2));
                add(Arrays.asList(1, 3));
                add(Arrays.asList(0, 2));
            }
        };
        assertThat(possibleCoordinates, is(expectedPossibleCoordinates));
    }
}
