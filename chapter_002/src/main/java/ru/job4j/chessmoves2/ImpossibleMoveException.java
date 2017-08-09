package ru.job4j.chessmoves2;

/**
 * Impossible move for figure's move Exception.
 */
public class ImpossibleMoveException extends Exception {
    /**
     * Instantiates a new Impossible move exception.
     *
     * @param msg the msg
     */
    public ImpossibleMoveException(String msg) {
        super(msg);
    }
}

