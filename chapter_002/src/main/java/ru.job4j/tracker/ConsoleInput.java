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
     * @param question the string of question
     * @return the string
     */
    public String ask(String question) {
        System.out.println(question);
        return scanner.nextLine();
    }

    /**
     * Ask number of action.
     * @param question the string of question
     * @param range the range of available actions
     * @return number of action
     */
    public int ask(String question, int[] range) {
        int key = Integer.valueOf(this.ask(question));
        boolean exist = false;
        for (int value : range) {
            if (value == key) {
                exist = true;
                break;
            }
        }
        if (exist) {
            return key;
        } else {
            throw new MenuOutException("Out of menu range.");
        }
    }
}
