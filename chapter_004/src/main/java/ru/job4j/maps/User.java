package ru.job4j.maps;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * The type User.
 */
public class User {
    /**
     * Name of the User.
     */
    private String name;
    /**
     * Amount of children of the User.
     */
    private int children;
    /**
     * The Birthday.
     */
    private Calendar birthday;

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param children the children
     * @param birthday the birthday
     */
    public User(String name, int children, Calendar birthday) {
        this.name = name;
        this.children = children;
        this.birthday = birthday;
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
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets children.
     *
     * @return the children
     */
    public int getChildren() {
        return children;
    }

    /**
     * Sets children.
     *
     * @param children the children
     */
    public void setChildren(int children) {
        this.children = children;
    }

    /**
     * Gets birthday.
     *
     * @return the birthday
     */
    public Calendar getBirthday() {
        return birthday;
    }

    /**
     * Sets birthday.
     *
     * @param birthday the birthday
     */
    public void setBirthday(Calendar birthday) {
        this.birthday = birthday;
    }

    @Override
    public String toString() {
        return "User [" + name + ", " + children + ", "
                + new SimpleDateFormat("yyyy-MM-dd").format(birthday.getTime()) + ']';
    }
}
