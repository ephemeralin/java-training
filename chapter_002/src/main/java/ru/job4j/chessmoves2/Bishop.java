package ru.job4j.chessmoves2;

/**
 * Created by ephemeralin on 04.08.17.
 */
public class Bishop extends Figure {

    /**
     * Instantiates a new Bishop.
     *
     * @param currentPosition the current position
     */
    Bishop(Cell currentPosition) {
        super(currentPosition);
    }

    @Override
    Cell[] way(Cell newPosition) throws ImpossibleMoveException {

        int currentX = getCurrentPosition().getX();
        int currentY = getCurrentPosition().getY();
        int newX = newPosition.getX();
        int newY = newPosition.getY();

        int sizeOfCellsArray = Math.abs(newX - currentX);

        int iteratorX = 1;
        if (newX < currentX) {
            iteratorX = -1;
        }

        int iteratorY = 1;
        if (newY < currentY) {
            iteratorY = -1;
        }

        Cell[] cells = new Cell[sizeOfCellsArray];

        int index = 0;
        while (currentX != newX && currentY != newY) {
            currentX += iteratorX;
            currentY += iteratorY;
            Cell cell = new Cell(currentX, currentY);
            cells[index] = cell;
            index++;
        }

        return cells;
    }
}
