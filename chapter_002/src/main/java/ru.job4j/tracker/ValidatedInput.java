package ru.job4j.tracker;

/**
 * Created by ephemeralin on 24.06.17.
 */
public class ValidatedInput extends ConsoleInput {
    /**
     * Ask number of action.
     * @param question the string of question
     * @param range the range of available actions
     * @return number of action
     */
    public int ask(String question, int[] range) {
        boolean valid = false;
        int value = -1;
        do {
            try {
                value = super.ask(question, range);
                valid = true;
            } catch (MenuOutException moe) {
                System.out.println("Please, enter the valid action from menu!");
            } catch (NumberFormatException nfe) {
                System.out.println("Please, enter valid data!");
            } catch (Exception e) {
                System.out.println("Unknown exception :(");
            }
        } while (!valid);
        return value;
    }
}
