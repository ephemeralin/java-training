package ru.job4j.chessmoves2;

/**
 * Occupied way for figure's move Exception.
 */
public class OccupiedWayException extends Exception {
    /**
     * Instantiates a new Impossible move exception.
     *
     * @param msg the msg
     */
    public OccupiedWayException(String msg) {
        super(msg);
    }
}

