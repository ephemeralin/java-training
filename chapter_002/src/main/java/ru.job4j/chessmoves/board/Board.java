package ru.job4j.chessmoves.board;

import ru.job4j.chessmoves.exceptions.ImpossibleMoveException;
import ru.job4j.chessmoves.figures.Figure;
import ru.job4j.chessmoves.figures.Bishop;


/**
 * The type board.
 */
public class Board {

    /**
     * The constant SIZE_OF_BOARD.
     */
    public static final int SIZE_OF_BOARD = 8;
    /**
     * The constant NUMBER_OF_FIGURES.
     */
    public static final int NUMBER_OF_FIGURES = 32;
    /**
     * All figures of the Board.
     */
    private Figure[] figures;
    /**
     * Cells which lays on the Board.
     */
    private Cell[][] cells = new Cell[SIZE_OF_BOARD][SIZE_OF_BOARD];
    /**
     * Letters of columns of Board.
     */
    private char[] columnLetters = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h'};
    /**
     * Instance of the Board. Singletone.
     */
    private static Board instance;

    /**
     * Constructor.
     */
    private Board() {
        this.figures = new Figure[NUMBER_OF_FIGURES];
        boolean isWhite = true;
        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            isWhite = !isWhite;
            for (int j = 0; j < SIZE_OF_BOARD; j++) {
                this.cells[i][j] = new Cell(isWhite, i + 1, columnLetters[j]);
                isWhite = !isWhite;
            }
        }
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static Board getInstance() {
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    /**
     * Gets next column letter.
     *
     * @param columnLetter the column letter
     * @return the next column letter
     */
    public char getNextColumnLetter(char columnLetter) {
        char result = '\0';
        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            if (columnLetters[i] == columnLetter) {
                int nextColumn = i + 1;
                if (nextColumn < SIZE_OF_BOARD) {
                    return columnLetters[nextColumn];
                }
            }
        }
        return result;
    }

    /**
     * Gets previous column letter.
     *
     * @param columnLetter the column letter
     * @return the previous column letter
     */
    public char getPreviousColumnLetter(char columnLetter) {
        char result = '\0';
        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            if (columnLetters[i] == columnLetter) {
                int prevColumn = i - 1;
                if (prevColumn >= 0) {
                    result = columnLetters[prevColumn];
                }
            }
        }
        return result;
    }

    /**
     * Gets next row number.
     *
     * @param rowNumber the row number
     * @return the next row number
     */
    public int getNextRowNumber(int rowNumber) {

        int nextColumn = rowNumber + 1;
        if (nextColumn < SIZE_OF_BOARD) {
            return nextColumn;
        } else {
            return 0;
        }
    }

    /**
     * Gets previous row number.
     *
     * @param rowNumber the row number
     * @return the previous row number
     */
    public int getPreviousRowNumber(int rowNumber)  {
        int prevRow = rowNumber - 1;
        if (prevRow > 0) {
            return prevRow;
        } else {
            return 0;
        }
    }

    /**
     * Find cell cell.
     *
     * @param row    the row
     * @param column the column
     * @return the cell
     * @throws ImpossibleMoveException the impossible move exception
     */
    public Cell findCell(int row, char column) throws ImpossibleMoveException {
        Cell result = null;
        for (int i = 0; i < SIZE_OF_BOARD; i++) {
            for (int j = 0; j < SIZE_OF_BOARD; j++) {
                if (this.cells[i][j].getRow() == row && this.cells[i][j].getColumn() == column) {
                    result = cells[i][j];
                    break;
                }
            }
        }
        if (result == null) {
            throw new ImpossibleMoveException("Impossible move exception!");
        } else {
            return result;
        }
    }

    /**
     * Find direction direction.
     *
     * @param startPosition  the start position
     * @param finishPosition the finish position
     * @return the direction
     */
    public Direction findDirection(Cell startPosition, Cell finishPosition) {
        Direction direction;

        int horizontalDirection = startPosition.getHorizontalDistanceTo(finishPosition);
        int verticalDirection = startPosition.getVerticalDistanceTo(finishPosition);

        if (horizontalDirection < 0 & verticalDirection == 0) {
            direction = Direction.RIGHT;
        } else if (horizontalDirection > 0 & verticalDirection == 0) {
            direction = Direction.LEFT;
        } else if (horizontalDirection == 0 & verticalDirection < 0) {
            direction = Direction.UP;
        } else if (horizontalDirection == 0 & verticalDirection > 0) {
            direction = Direction.DOWN;
        } else if (horizontalDirection < 0 & verticalDirection < 0) {
            direction = Direction.RIGHT_UP;
        } else if (horizontalDirection < 0 & verticalDirection > 0) {
            direction = Direction.RIGHT_DOWN;
        } else if (horizontalDirection > 0 & verticalDirection < 0) {
            direction = Direction.LEFT_UP;
        } else if (horizontalDirection > 0 & verticalDirection > 0) {
            direction = Direction.LEFT_DOWN;
        } else {
            direction = Direction.NONE;
        }

        return direction;
    }

    /**
     * Choose figure on cell figure.
     *
     * @param row    the row
     * @param column the column
     * @return the figure
     * @throws ImpossibleMoveException the impossible move exception
     */
    public Figure chooseFigureOnCell(int row, char column) throws ImpossibleMoveException {
        Figure figure = null;

        Cell cellOfFigure = null;
        Cell cell = this.findCell(row, column);

        for (int i = 0; i < figures.length; i++) {
            cellOfFigure = figures[i].getPosition();
            if (cellOfFigure.equals(cell)) {
                figure = figures[i];
                break;
            }
        }

        if (figure == null) {
            throw new ImpossibleMoveException("Figure on that cell does not exists!");
        } else {
            return figure;
        }

    }

    /**
     * Sets .
     *
     * @param isWhite the is white
     * @throws ImpossibleMoveException the impossible move exception
     */
    public void setup(boolean isWhite) throws ImpossibleMoveException {
        int firstLine;
        int secondLine;

        if (isWhite) {
            firstLine = 1;
            secondLine = 2;
        } else {
            firstLine = 8;
            secondLine = 7;
        }

        //Bishops
        this.figures[0] = new Bishop(this, findCell(firstLine, 'c'), isWhite);
        this.figures[1] = new Bishop(this, findCell(firstLine, 'f'), isWhite);
    }
}
