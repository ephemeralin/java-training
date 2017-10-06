package ru.job4j.maps;

import java.util.Calendar;

/**
 * The type User.
 */
public class UserWithOverridingHashcode extends User {

    /**
     * Default constructor.
     * @param name name
     * @param children children
     * @param birthday date of birth
     */
    public UserWithOverridingHashcode(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getChildren();
        result = 31 * result + (getBirthday() != null ? getBirthday().hashCode() : 0);
        return result;
    }
}
