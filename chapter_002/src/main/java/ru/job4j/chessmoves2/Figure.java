package ru.job4j.chessmoves2;

/**
 * Created by ephemeralin on 04.08.17.
 */
public abstract class Figure {
    /**
     * Current position of the figure.
     */
    private Cell currentPosition;

    /**
     * Instantiates a new Figure.
     *
     * @param currentPosition the current position
     */
    Figure(Cell currentPosition) {
        this.currentPosition = currentPosition;
    }

    /**
     * Gets current position.
     *
     * @return the current position
     */
    public Cell getCurrentPosition() {
        return currentPosition;
    }

    /**
     * Sets current position.
     *
     * @param position the position
     */
    public void setCurrentPosition(Cell position) {
        this.currentPosition = position;
    }

    /**
     * Way cell [ ].
     *
     * @param newPosition the new position
     * @return the cell [ ]
     * @throws ImpossibleMoveException the impossible move exception
     */
    abstract Cell[] way(Cell newPosition) throws ImpossibleMoveException;
}