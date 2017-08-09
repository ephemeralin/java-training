package ru.job4j.chessmoves.figures;


import ru.job4j.chessmoves.board.Board;
import ru.job4j.chessmoves.exceptions.ImpossibleMoveException;
import ru.job4j.chessmoves.board.Cell;
import ru.job4j.chessmoves.exceptions.OccupiedWayException;

/**
 * The type Figure.
 */
public abstract class Figure {
    /**
     * Position of the Figure.
     */
    private Cell position;
    /**
     * Is white or not flag.
     */
    private boolean isWhite;
    /**
     * String name of the Figure.
     */
    private String name;
    /**
     * Board where the Figure lays.
     */
    private Board board;

    /**
     * Instantiates a new Figure.
     *
     * @param board    the board
     * @param name     the name
     * @param position the position
     * @param white    the white
     */
    public Figure(Board board, String name, Cell position, boolean white) {
        this.board = Board.getInstance();
        this.position = position;
        this.isWhite = white;
        this.name = name;
    }


    /**
     * Find possible positions cell [].
     *
     * @return the cell []
     * @throws ImpossibleMoveException the impossible move exception
     */
    public abstract Cell[] findPossiblePositions() throws ImpossibleMoveException;

    /**
     * Move is possible boolean.
     *
     * @param newPosition the new position
     * @return the boolean
     * @throws ImpossibleMoveException the impossible move exception
     */
    public abstract boolean moveIsPossible(Cell newPosition) throws ImpossibleMoveException;

    /**
     * Move cell [ ].
     *
     * @param newPosition the new position
     * @return the cell [ ]
     * @throws ImpossibleMoveException the impossible move exception
     * @throws OccupiedWayException    the occupied way exception
     */
    public abstract Cell[] move(Cell newPosition) throws ImpossibleMoveException, OccupiedWayException;


    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(Cell position) {
        this.position = position;
        this.position.setOccupied(true);
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Cell getPosition() {
        return position;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets full name.
     *
     * @return the full name
     */
    public String getFullName() {
        String colorString = isWhite() ? "White" : "Black";
        String nameString = getName();
        String positionString = getPosition().getStringPosition();
        String s = String.format("%s %s on %s", colorString, nameString, positionString);
        return s;
    }

    /**
     * Is white boolean.
     *
     * @return the boolean
     */
    public boolean isWhite() {
        return isWhite;
    }

    /**
     * Sets white.
     *
     * @param white the white
     */
    public void setWhite(boolean white) {
        isWhite = white;
    }

}

