package ru.job4j.bomberman;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

/**
 * The type Creature.
 */
public abstract class Creature {

    /**
     * Name.
     */
    private String name;
    /**
     * X coordinate.
     */
    private int x;
    /**
     * Y coordinate.
     */
    private int y;
    /**
     * Board.
     */
    private Board board;

    /**
     * Instantiates a new Creature.
     *
     * @param name  the name
     * @param x     the x
     * @param y     the y
     * @param board the board
     */
    public Creature(String name, int x, int y, Board board) {
        this.x = x;
        this.y = y;
        this.name = name;
        this.board = board;
    }

    /**
     * Gets possible coordinates.
     *
     * @return the possible coordinates
     */
    public HashSet<List<Integer>> getPossibleCoordinates() {
        int maxX = this.board.getMaxX();
        int maxY = this.board.getMaxY();

        HashSet<List<Integer>> possibleCoordinates = new HashSet<>();
        if (getX() - 1 >= 0) {
            possibleCoordinates.add(Arrays.asList(getX() - 1, getY()));
        }
        if (getY() - 1 >= 0) {
            possibleCoordinates.add(Arrays.asList(getX(), getY() - 1));
        }
        if (getX() + 1 <= maxX) {
            possibleCoordinates.add(Arrays.asList(getX() + 1, getY()));
        }
        if (getY() + 1 <= maxY) {
            possibleCoordinates.add(Arrays.asList(getX(), getY() + 1));
        }

        return possibleCoordinates;
    }

    /**
     * Move to boolean.
     *
     * @param newX the new x
     * @param newY the new y
     * @return the boolean
     */
    public boolean moveTo(int newX, int newY) {
        boolean isMoved = false;
        try {
            if (!board.getCell(newX, newY).lockCell()) {
                System.err.println(String.format("      %s cannot move to (%d, %d)", this.name, newX, newY));
            } else {
                board.getCell(this.x, this.y).unlock();
                System.out.println(String.format("  %s released (%d, %d)", this.name, this.x, this.y));
                this.x = newX;
                this.y = newY;
                System.out.println(String.format("%s moved to (%d, %d)", this.name, newX, newY));
                isMoved = true;
            }
        } catch (IndexOutOfBoundsException e) {
            System.err.println(String.format("%s cannot set with coordinates (%d, %d)", this.name, newX, newY));
        } catch (InterruptedException e) {
            System.err.println("Operation was interrupted.");
        } catch (Exception e) {
            System.err.println(String.format("Unknown exception when %s is being set on the Board.", this.name));
            e.printStackTrace();
        }
        return isMoved;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return this.x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return this.y;
    }

}
