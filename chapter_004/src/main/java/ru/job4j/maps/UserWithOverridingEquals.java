package ru.job4j.maps;

import java.util.Calendar;

/**
 * The type User with overriding of equals method.
 */
public class UserWithOverridingEquals extends User {

    /**
     * Default constructor.
     * @param name name
     * @param children children
     * @param birthday date of birth
     */
    public UserWithOverridingEquals(String name, int children, Calendar birthday) {
        super(name, children, birthday);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        User user = (User) o;

        if (getChildren() != user.getChildren()) {
            return false;
        }
        if (getName() != null ? !getName().equals(user.getName()) : user.getName() != null) {
            return false;
        }
        return getBirthday() != null ? getBirthday().equals(user.getBirthday()) : user.getBirthday() == null;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
