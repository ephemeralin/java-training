package ru.job4j.inheritance;

/**
 * Created by ephemeralin on 15.05.17.
 */
public abstract class Human {

    /**
     * The Name.
     */
    private String name;
    /**
     * The Age.
     */
    private int age;
    /**
     * The Location.
     */
    private String location;
    /**
     * The Status.
     */
    private String status;


    /**
     * Instantiates a new Human.
     *
     * @param name     the name
     * @param age      the age
     * @param location the location
     * @param status   the status
     */
    public Human(String name, int age, String location, String status) {
        this.name = name;
        this.age = age;
        this.location = location;
        this.status = status;
    }

    /**
     * Eat.
     */
    public void eat() {

    }

    /**
     * Sleep.
     */
    public void sleep() {

    }

    /**
     * Go.
     */
    public void go() {

    }

    /**
     * Seat.
     */
    public void seat() {

    }

    /**
     * Stand.
     */
    public void stand() {

    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Sets age.
     *
     * @param age the age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets age.
     *
     * @return the age
     */
    public int getAge() {
        return age;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }
}
