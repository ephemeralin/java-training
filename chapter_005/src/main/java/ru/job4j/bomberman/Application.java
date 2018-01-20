package ru.job4j.bomberman;

/**
 * The type Application.
 */
public class Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws InterruptedException the interrupted exception
     */
    public static void main(String[] args) throws InterruptedException {
        Application game = new Application();
        game.startGame(9, 7, 3, 2);
    }

    /**
     * Start game.
     *
     * @param boardSizeColumns the board size columns
     * @param boardSizeRows    the board size rows
     * @param stones           the stones
     * @param monsters         the monsters
     * @throws InterruptedException the interrupted exception
     */
    public void startGame(int boardSizeColumns, int boardSizeRows, int stones, int monsters) throws InterruptedException {
        Board board = new Board(boardSizeColumns, boardSizeRows, stones);

        board.generateStones();
        board.generateMonsters(monsters);

        Player player = board.createPlayer();
    }
}
