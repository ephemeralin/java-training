package ru.job4j.strategy;

/**
 * Created by ephemeralin on 04.06.17.
 */
public class Triangle implements DrawStrategy {

    /**
     * Implementation of draw strategy for Triangle.
     *
     * @return the picture of the shape
     */
    public String pic() {
        StringBuilder sb = new StringBuilder();
        final String separator = System.getProperty("line.separator");
        sb.append(" *").append(separator).append("***");
        return sb.toString();
    }
}
