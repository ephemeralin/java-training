package ru.job4j.bomberman;

/**
 * The type Coordinates.
 */
public class Coordinates {
    /**
     * X.
     */
    private final int x;
    /**
     * Y.
     */
    private final int y;

    /**
     * Instantiates a new Coordinates.
     *
     * @param x the x
     * @param y the y
     */
    public Coordinates(int x, int y) {
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
}
