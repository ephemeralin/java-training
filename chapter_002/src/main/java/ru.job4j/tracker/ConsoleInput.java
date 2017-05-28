package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Created by ephemeralin on 27.05.17.
 */
public class ConsoleInput implements Input {
    /**
     * Scanner for user input.
     */
    private Scanner scanner = new Scanner(System.in);

    /**
     * Ask string.
     *
     * @return the string
     */
    public String ask() {
        return scanner.nextLine();
    }

    /**
     * Ask string.
     *
     * @param question the question
     * @return the string
     */
    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
