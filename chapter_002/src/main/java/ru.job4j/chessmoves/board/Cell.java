package ru.job4j.chessmoves.board;

/**
 * Created by ephemeralin on 03.07.17.
 * Cell class.
 */
public class Cell {
    /**
     * Is white or not flag.
     */
    private boolean isWhite;

    /**
     * Number of chessboard's row.
     */
    private int row;

    /**
     * Column of chessboard.
     */
    private char column;

    /**
     * Cell can be occupied by figure.
     */
    private boolean occupied;

    /**
     * Instantiates a new Cell.
     *
     * @param isWhite the is white
     * @param row     the row
     * @param file    the file
     */
    public Cell(boolean isWhite, int row, char file) {
        this.isWhite = isWhite;
        this.row = row;
        this.column = file;
        this.occupied = false;
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

    /**
     * Gets row.
     *
     * @return the row
     */
    public int getRow() {
        return row;
    }

    /**
     * Sets row.
     *
     * @param row the row
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Gets column.
     *
     * @return the column
     */
    public char getColumn() {
        return column;
    }

    /**
     * Sets column.
     *
     * @param column the column
     */
    public void setColumn(char column) {
        this.column = column;
    }

    /**
     * Sets occupied.
     *
     * @param occupied the occupied
     */
    public void setOccupied(boolean occupied) {
        this.occupied = occupied;
    }

    /**
     * Is occupied boolean.
     *
     * @return the boolean
     */
    public boolean isOccupied() {
        return this.occupied;
    }

    /**
     * Gets horizontal distance to.
     *
     * @param cell the cell
     * @return the horizontal distance to
     */
    public int getHorizontalDistanceTo(Cell cell) {
        return this.getColumn() - cell.getColumn();
    }

    /**
     * Gets vertical distance to.
     *
     * @param cell the cell
     * @return the vertical distance to
     */
    public int getVerticalDistanceTo(Cell cell) {
        return this.getRow() - cell.getRow();
    }

    /**
     * Gets string position.
     *
     * @return the string position
     */
    public String getStringPosition() {
        return String.format("%s%d", this.getColumn(), this.getRow());
    }
}
