package ru.job4j.bomberman;

/**
 * The type Player.
 */
public class Player extends Creature  {
    /**
     * Instantiates a new Player.
     *
     * @param name  the name
     * @param x     the x
     * @param y     the y
     * @param board the board
     */
    public Player(String name, int x, int y, Board board) {
        super(name, x, y, board);
    }

    /**
     * Move up boolean.
     *
     * @return the boolean
     */
    public boolean moveUp() {
        return moveTo(this.getX(), this.getY() - 1);
    }

    /**
     * Move down boolean.
     *
     * @return the boolean
     */
    public boolean moveDown() {
        return moveTo(this.getX(), this.getY() + 1);
    }

    /**
     * Move left boolean.
     *
     * @return the boolean
     */
    public boolean moveLeft() {
        return moveTo(this.getX() - 1, this.getY());
    }

    /**
     * Move right boolean.
     *
     * @return the boolean
     */
    public boolean moveRight() {
        return moveTo(this.getX() + 1, this.getY());
    }
}
