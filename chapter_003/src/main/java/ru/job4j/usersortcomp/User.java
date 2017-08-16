package ru.job4j.usersortcomp;


/**
 * The type User.
 */
public class User {
    /**
     * Name.
     */
    private String name;
    /**
     * Age.
     */
    private int age;

    /**
     * Instantiates a new User.
     *
     * @param name the name
     * @param age  the age
     */
    public User(String name, int age) {
        this.name = name;
        this.age = age;
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
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Overrided toString.
     * @return String for User
     */
    public String toString() {
        return String.format("(%s, %d)", this.name, this.age);
    }
}
