package ru.job4j.chessmoves2;

/**
 * Created by ephemeralin on 04.08.17.
 */
public class Board {
    /**
     * The constant SIZE.
     */
    public static final int SIZE = 8;
    /**
     * The constant NUMBER OF FIGURES.
     */
    public static final int NUMBER_OF_FIGURES = 32;

    /**
     * The Figures on board.
     */
    private Figure[] figuresOnBoard;

    /**
     * The Index of the last added figure.
     */
    private int indexOfLastAddedFigure;

    /**
     * Instantiates a new Board.
     */
    Board() {
        this.figuresOnBoard = new Figure[NUMBER_OF_FIGURES];
        this.indexOfLastAddedFigure = 0;
    }


    /**
     * Set.
     *
     * @param figure the figure
     */
    public void set(Figure figure) {
        this.figuresOnBoard[indexOfLastAddedFigure] = figure;
        indexOfLastAddedFigure++;
    }

    /**
     * Find Figure on Cell.
     * @param cell where try finding a figure
     * @return found Figure
     * @throws FigureNotFoundException the figure not found exception
     */
    private Figure findFigureOnCell(Cell cell) throws FigureNotFoundException {
        Figure foundFigure = null;

        for (Figure figure : figuresOnBoard) {
            if (figure.getCurrentPosition().equals(cell)) {
                foundFigure = figure;
                break;
            }
        }
        if (foundFigure == null) {
            throw new FigureNotFoundException("There is no figure!");
        }
        return foundFigure;
    }

    /**
     * Move the Figure, boolean.
     *
     * @param currentPosition the current position
     * @param newPosition     the new position
     * @return the boolean
     * @throws ImpossibleMoveException the impossible move exception
     * @throws OccupiedWayException    the occupied way exception
     * @throws FigureNotFoundException the figure not found exception
     */
    public Boolean move(Cell currentPosition, Cell newPosition) throws ImpossibleMoveException, OccupiedWayException, FigureNotFoundException {

        Figure currentFigure = findFigureOnCell(currentPosition);
        boolean moveIsPossible = false;

        if (newPosition.equals(currentPosition)) {
            throw new ImpossibleMoveException("You have to move the figure!");
        }
        Cell[] cellsWay = currentFigure.way(newPosition);
        for (Cell cell : cellsWay) {
            for (Figure figure : figuresOnBoard) {
                if (figure == null) {
                    break;
                }
                if (!figure.equals(currentFigure)) {
                    if (figure.getCurrentPosition().equals(cell)) {
                        throw new OccupiedWayException("The way is occupied!");
                    }
                }

            }
            if (newPosition.isSameTo(cell)) {
                moveIsPossible = true;
            }
        }

        if (moveIsPossible) {
            currentFigure.setCurrentPosition(newPosition);
        }

        return moveIsPossible;
    }


}
