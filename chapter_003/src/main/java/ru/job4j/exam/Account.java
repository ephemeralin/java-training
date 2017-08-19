package ru.job4j.exam;


/**
 * The type Account.
 */
public class Account {
    /**
     * Value of money.
     */
    private double value;
    /**
     * Requisities.
     */
    private String requisites;

    /**
     * Gets value.
     *
     * @return the value
     */
    public double getValue() {
        return value;
    }

    /**
     * Sets value.
     *
     * @param value the value
     */
    public void setValue(double value) {
        this.value = value;
    }

    /**
     * Gets requisites.
     *
     * @return the requisites
     */
    public String getRequisites() {
        return requisites;
    }

    /**
     * Sets requisites.
     *
     * @param requisites the requisites
     */
    public void setRequisites(String requisites) {
        this.requisites = requisites;
    }

    /**
     * Instantiates a new Account.
     *
     * @param value      the value
     * @param requisites the requisites
     */
    public Account(double value, String requisites) {
        this.value = value;
        this.requisites = requisites;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", this.requisites, this.value);
    }


}
