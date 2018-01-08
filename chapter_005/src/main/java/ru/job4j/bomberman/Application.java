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
        Board board = new Board(4, 4);

        Monster monsterOne = new Monster("Monster 1", 1, 2, board);
        if (monsterOne != null) {
            Thread thread2 = new Thread(monsterOne);
            thread2.start();
        }

        Thread.sleep(300);

        Monster monsterTwo = new Monster("Monster 2", 0, 1, board);
        if (monsterTwo != null) {
            Thread thread2 = new Thread(monsterTwo);
            thread2.start();
        }
    }
}
