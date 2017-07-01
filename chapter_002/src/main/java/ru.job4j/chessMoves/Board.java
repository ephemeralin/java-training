package ru.job4j.chessMoves;

public class Board {

    private int sizeOfBoard;
    private Cell[][] cells;

    public static class Cell {
    }

    public Board(int sizeOfBoard) {
        this.cells = new Cell[sizeOfBoard][sizeOfBoard];
    }
}
