package ru.job4j.chessmoves2;

/**
 * Created by ephemeralin on 04.08.17.
 */
public class Cell {

    /**
     * x coordinate.
     */
    private final int x;
    /**
     * y coordinate.
     */
    private final int y;

    /**
     * Instantiates a new Cell.
     *
     * @param x the x
     * @param y the y
     */
    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Gets x.
     *
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * Gets y.
     *
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * Overrided toString().
     * @return string of cell
     */
    @Override
    public String toString() {
        return String.format("x%dy%d", getX(), getY());
    }

    /**
     * Is same to boolean.
     *
     * @param otherCell the other cell
     * @return the boolean
     */
    public boolean isSameTo(Cell otherCell) {
        return this.getX() == otherCell.getX() && this.getY() == otherCell.getY();
    }
}
