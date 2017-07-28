package ru.job4j.chessmoves;


import ru.job4j.chessmoves.board.Board;
import ru.job4j.chessmoves.exceptions.ImpossibleMoveException;

/**
 * The type Application.
 */
public class Application {

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Board board = Board.getInstance();
        try {
            board.setup(true);
        } catch (ImpossibleMoveException e) {
            e.printStackTrace();
        }

    }
}
