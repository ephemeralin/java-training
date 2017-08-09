package ru.job4j.performance;


/**
 * Created by ephemeralin on 05.08.17.
 */
public class Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        int amount = 1000000;
        CollectionsPerformanceAnalyzer cpa = new CollectionsPerformanceAnalyzer(amount);
        cpa.checkAdd();
        cpa.checkDelete();
    }
}
