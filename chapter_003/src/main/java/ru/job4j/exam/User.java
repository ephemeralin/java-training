package ru.job4j.exam;


/**
 * The type User.
 */
public class User implements Comparable<User> {
    /**
     * Name.
     */
    private String name;
    /**
     * Passport.
     */
    private String passport;

    /**
     * Instantiates a new User.
     *
     * @param name     the name
     * @param passport the passport
     */
    public User(String name, String passport) {
        this.name = name;
        this.passport = passport;
    }

    @Override
    public String toString() {
        return String.format("%s (%s)", this.name, this.passport);
    }

    /**
     * Gets passport.
     *
     * @return the passport
     */
    public String getPassport() {
        return passport;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {

        return name;
    }

    @Override
    public int compareTo(User user) {
        int compareNames = this.name.compareTo(user.getName());
        if (compareNames != 0) {
            return compareNames;
        } else {
            return this.passport.compareTo(user.getPassport());
        }
    }

    /**
     * Equals boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean equals(User user) {
        return this.name.compareTo(user.getName()) == 0 & this.passport.compareTo(user.getPassport()) == 0;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
