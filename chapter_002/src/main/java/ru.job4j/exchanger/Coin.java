package ru.job4j.exchanger;

/**
 * Created by ephemeralin on 31.07.17.
 */
public class Coin {
    /**
     * Value of the Coin.
     */
    private int value;
    /**
     * String name of the coin.
     */
    private String name;

    /**
     * Instantiates a new Coin.
     *
     * @param value the value
     */
    Coin(int value) {
        this.value = value;
        this.name = String.format("%s rub", String.valueOf(value));
    }

    /**
     * Gets value.
     *
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }
}
