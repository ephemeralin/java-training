package ru.job4j.usersort;


/**
 * The type User.
 */
public class User implements Comparable<User>{
    private String name;
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

    @Override
    public int compareTo(User user) {
        return Integer.valueOf(this.age).compareTo(Integer.valueOf(user.getAge()));
    }

    public String toString() {
        return String.format("(%s, %d)", this.name, this.age);
    }
}
