package ru.job4j.bomberman;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 * The type Monster.
 */
public class Monster extends Creature implements Runnable {

    /**
     * Instantiates a new Monster.
     *
     * @param name  the name
     * @param x     the x
     * @param y     the y
     * @param board the board
     */
    public Monster(String name, int x, int y, Board board) {
        super(name, x, y, board);
    }

    /**
     * Random move.
     */
    public void randomMove() {
        HashSet<List<Integer>> possibleCoordinates = getPossibleCoordinates();

        List<Integer> randomCoordinates;
        Iterator<List<Integer>> iter;

        do {
            int index = new Random().nextInt(possibleCoordinates.size());

            iter = possibleCoordinates.iterator();
            for (int i = 0; i < index; i++) {
                iter.next();
            }
            randomCoordinates = iter.next();
        } while (!moveTo(randomCoordinates.get(0), randomCoordinates.get(1)));
    }

    @Override
    public void run() {
        moveTo(getX(), getY());
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            randomMove();
        }

    }
}
