package ru.job4j.bomberman;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;

/**
 * The type Board.
 */
public final class Board {
    /**
     * Container for keeping cells.
     */
    private final Cell[][] container;

    /**
     * Stones on the Board.
     */
    private final Stone[] stones;
    /**
     * Max X coordinate.
     */
    private final int maxX;
    /**
     * Max Y coordinate.
     */
    private final int maxY;

    /**
     * Maximum number of stones on the Board.
     */
    private final int stonesThreshold;

    /**
     * Instantiates a new Board.
     *
     * @param columns         the columns
     * @param rows            the rows
     * @param stonesThreshold the stones threshold
     */
    public Board(int columns, int rows, int stonesThreshold) {
        this.stonesThreshold = stonesThreshold;
        this.stones = new Stone[stonesThreshold];

        this.container = new Cell[columns][rows];
        for (int i = 0; i < this.container.length; i++) {
            for (int j = 0; j < this.container[i].length; j++) {
                this.container[i][j] = new Cell();
            }
        }
        this.maxX = columns - 1;
        this.maxY = rows - 1;
    }

    /**
     * Gets cell.
     *
     * @param x the x
     * @param y the y
     * @return the cell
     */
    public Cell getCell(int x, int y) {
        return this.container[x][y];
    }

    /**
     * Gets max x.
     *
     * @return the max x
     */
    public int getMaxX() {
        return maxX;
    }

    /**
     * Gets max y.
     *
     * @return the max y
     */
    public int getMaxY() {
        return maxY;
    }

    /**
     * Lock free cell coordinates.
     *
     * @return the coordinates
     * @throws InterruptedException the interrupted exception
     */
    public Coordinates lockFreeCell() throws InterruptedException {
        HashSet<Coordinates> coordinatesOfAllCells = getCoordinatesOfAllCells();
        Coordinates coordinates;
        Iterator<Coordinates> iter;
        boolean isLocked = false;
        do {
            int index = new Random().nextInt(coordinatesOfAllCells.size());

            iter = coordinatesOfAllCells.iterator();
            for (int j = 0; j < index; j++) {
                iter.next();
            }
            coordinates = iter.next();
            if (this.getCell(coordinates.getX(), coordinates.getY()).lockCell()) {
                isLocked = true;
            }
        } while (!isLocked);
        return coordinates;
    }

    /**
     * Generate stones.
     *
     * @throws InterruptedException the interrupted exception
     */
    public void generateStones() throws InterruptedException {
        for (int i = 0; i < this.stonesThreshold; i++) {
            Coordinates freeCell = lockFreeCell();
            stones[i] = new Stone(freeCell.getX(), freeCell.getY());
        }
    }

    /**
     * Generate monsters.
     *
     * @param number the number
     * @throws InterruptedException the interrupted exception
     */
    public void generateMonsters(int number) throws InterruptedException {
        for (int i = 1; i <= number; i++) {
            Coordinates freeCell = this.lockFreeCell();
            Monster monster = new Monster(String.format("Monster %d", i), freeCell.getX(), freeCell.getY(), this);
            if (monster != null) {
                new Thread(monster).start();
            }
            Thread.sleep(200);
        }
    }

    /**
     * Create player.
     *
     * @return the player
     * @throws InterruptedException the interrupted exception
     */
    public Player createPlayer() throws InterruptedException {
        Coordinates freeCell = lockFreeCell();
        return new Player("Player", freeCell.getX(), freeCell.getY(), this);
    }

    /**
     * Get stones.
     *
     * @return the stone[]
     */
    public Stone[] getStones() {
        return stones;
    }

    /**
     * Get coordinates of all cells on the Board.
     * @return coordinates
     */
    private HashSet<Coordinates> getCoordinatesOfAllCells() {
        HashSet<Coordinates> coordinatesOfAllCells = new HashSet<>();
        for (int i = 0; i < this.container.length; i++) {
            for (int j = 0; j < this.container[i].length; j++) {
                Coordinates coordinates = new Coordinates(i, j);
                coordinatesOfAllCells.add(coordinates);
            }
        }
        return coordinatesOfAllCells;
    }

    /**
     * The Stone.
     */
    public class Stone {
        /**
         * X.
         */
        private final int x;
        /**
         * Y.
         */
        private final int y;

        /**
         * Instantiates a new Stone.
         *
         * @param x the x
         * @param y the y
         */
        public Stone(int x, int y) {
                this.x = x;
                this.y = y;
            }
        }
    }
