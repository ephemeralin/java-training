package ru.job4j.chessmoves.figures;

import ru.job4j.chessmoves.board.Board;
import ru.job4j.chessmoves.exceptions.ImpossibleMoveException;
import ru.job4j.chessmoves.board.Cell;
import ru.job4j.chessmoves.board.Direction;
import ru.job4j.chessmoves.exceptions.OccupiedWayException;

/**
 * Created by ephemeralin on 05.07.17.
 */
public class Bishop extends Figure {

    /**
     * Board of the Game.
     */
    private Board board;

    /**
     * Instantiates a new Bishop.
     *
     * @param board    the board
     * @param position the position
     * @param white    the white
     */
    public Bishop(Board board, Cell position, boolean white) {
        super(board, "Bishop", position, white);
        this.board = board;
    }

    /**
     * Find possible positions cell [].
     *
     * @return the cell []
     * @throws ImpossibleMoveException the impossible move exception
     */
    public Cell[] findPossiblePositions() throws ImpossibleMoveException {
        Cell currentPosition = this.getPosition();
        Cell[] possiblePositions = new Cell[13];

        int row;
        char column;
        int i = 0;
        Cell stepCell;

        //up and right
        row = currentPosition.getRow();
        column = currentPosition.getColumn();
        while (true) {
            row = board.getNextRowNumber(row);
            column = board.getNextColumnLetter(column);
            if (row != 0 & column != '\0') {
                stepCell = board.findCell(row, column);
                if (!stepCell.isOccupied()) {
                    possiblePositions[i] = stepCell;
                    i++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        //up and left
        row = currentPosition.getRow();
        column = currentPosition.getColumn();
        while (true) {
            row = board.getNextRowNumber(row);
            column = board.getPreviousColumnLetter(column);
            if (row != 0 & column != '\0') {
                stepCell = board.findCell(row, column);
                if (!stepCell.isOccupied()) {
                    possiblePositions[i] = stepCell;
                    i++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        //down and right
        row = currentPosition.getRow();
        column = currentPosition.getColumn();
        while (true) {
            row = board.getPreviousRowNumber(row);
            column = board.getNextColumnLetter(column);
            if (row != 0 & column != '\0') {
                stepCell = board.findCell(row, column);
                if (!stepCell.isOccupied()) {
                    possiblePositions[i] = stepCell;
                    i++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        //down and left
        row = currentPosition.getRow();
        column = currentPosition.getColumn();
        while (true) {
            row = board.getPreviousRowNumber(row);
            column = board.getPreviousColumnLetter(column);
            if (row != 0 & column != '\0') {
                stepCell = board.findCell(row, column);
                if (!stepCell.isOccupied()) {
                    possiblePositions[i] = stepCell;
                    i++;
                } else {
                    break;
                }
            } else {
                break;
            }
        }

        return possiblePositions;
    }

    /**
     * Move is possible boolean.
     *
     * @param newPosition the new position
     * @return the boolean
     * @throws ImpossibleMoveException the impossible move exception
     */
    public boolean moveIsPossible(Cell newPosition) throws ImpossibleMoveException {
        boolean isPossible = false;
        Cell[] possiblePositions = findPossiblePositions();
        for (int i = 0; i < possiblePositions.length; i++) {
            if (newPosition.equals(possiblePositions[i])) {
                isPossible = true;
                break;
            }
        }
        return isPossible;
    }

    /**
     * Move cell [ ].
     *
     * @param newPosition the new position
     * @return the cell [ ]
     * @throws ImpossibleMoveException the impossible move exception
     * @throws OccupiedWayException    the occupied way exception
     */
    public Cell[] move(Cell newPosition) throws ImpossibleMoveException, OccupiedWayException {
        Cell[] pathCells;
        if (!moveIsPossible(newPosition)) {
            throw new ImpossibleMoveException("This move can't be possible!");

        } else {
            Cell currentPosition = this.getPosition();
            int numberOfPathCells = Math.abs(currentPosition.getHorizontalDistanceTo(newPosition));
            pathCells = new Cell[numberOfPathCells];

            int i = 0;
            int row = currentPosition.getRow();
            char column = currentPosition.getColumn();

            Direction direction = board.findDirection(currentPosition, newPosition);
            switch (direction) {
                case RIGHT_UP:
                    do {
                        row = board.getNextRowNumber(row);
                        column = board.getNextColumnLetter(column);
                        currentPosition = board.findCell(row, column);
                        pathCells[i] = currentPosition;
                        i++;
                    } while (!currentPosition.equals(newPosition));
                    break;
                case RIGHT_DOWN:
                    do {
                        row = board.getNextRowNumber(row);
                        column = board.getPreviousColumnLetter(column);
                        currentPosition = board.findCell(row, column);
                        pathCells[i] = currentPosition;
                        i++;
                    } while (!currentPosition.equals(newPosition));
                    break;
                case LEFT_UP:
                    do {
                        row = board.getPreviousRowNumber(row);
                        column = board.getNextColumnLetter(column);
                        currentPosition = board.findCell(row, column);
                        pathCells[i] = currentPosition;
                        i++;
                    } while (!currentPosition.equals(newPosition));
                    break;
                case LEFT_DOWN:
                    do {
                        row = board.getPreviousRowNumber(row);
                        column = board.getPreviousColumnLetter(column);
                        currentPosition = board.findCell(row, column);
                        pathCells[i] = currentPosition;
                        i++;
                    } while (!currentPosition.equals(newPosition));
                    break;
                case NONE:
                    throw new ImpossibleMoveException("Your turn! Be more confident :)");
                default:
                    throw new ImpossibleMoveException("Unknown impossible move exception :(");
            }

            this.setPosition(newPosition);
        }

        return pathCells;
    }
}