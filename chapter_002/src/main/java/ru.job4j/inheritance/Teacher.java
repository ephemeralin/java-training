package ru.job4j.inheritance;

/**
 * Created by ephemeralin on 15.05.17.
 */
public class Teacher extends Human {
    /**
     * The Experience.
     */
    private int experience;

    /**
     * Instantiates a new Teacher.
     *
     * @param name     the name
     * @param age      the age
     * @param location the location
     * @param status   the status
     */
    public Teacher(String name, int age, String location, String status) {
        super(name, age, location, status);
    }

    /**
     * Teach.
     */
    public void teach() {

    }

    /**
     * Test.
     */
    public void test() {

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
