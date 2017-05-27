package ru.job4j.tracker;

import java.util.Scanner;

/**
 * Created by ephemeralin on 27.05.17.
 */
public class ConsoleInput implements Input {
    private Scanner scanner = new Scanner(System.in);

    public String ask() {
        return scanner.nextLine();
    }

    public String ask(String question) {
        System.out.print(question);
        return scanner.nextLine();
    }
}
