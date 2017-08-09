package ru.job4j.inheritance;

/**
 * Created by ephemeralin on 15.05.17.
 */
public class Engineer extends Human {
    /**
     * The Experience.
     */
    private int experience;

    /**
     * Instantiates a new Engineer.
     *
     * @param name     the name
     * @param age      the age
     * @param location the location
     * @param status   the status
     */
    public Engineer(String name, int age, String location, String status) {
        super(name, age, location, status);
    }

    /**
     * Invent.
     */
    public void invent() {

    }

    /**
     * Draw.
     */
    public void draw() {

    }

    /**
     * Construct.
     */
    public void construct() {

    }

    /**
     * Gets experience.
     *
     * @return the experience
     */
    public int getExperience() {
        return experience;
    }
}