package ru.job4j.bomberman;

/**
 * The type Board.
 */
public final class Board {
    /**
     * Container for keeping cells.
     */
    private final Cell[][] container;
    /**
     * Max X coordinate.
     */
    private final int maxX;
    /**
     * Max Y coordinate.
     */
    private final int maxY;

    /**
     * Instantiates a new Board.
     *
     * @param columns the columns
     * @param rows    the rows
     */
    public Board(int columns, int rows) {
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
}
