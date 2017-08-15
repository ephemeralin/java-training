package ru.job4j.usersort;
import java.util.Set;
import java.util.List;
import java.util.TreeSet;

/**
 * The type Sort user.
 */
public class SortUser {

    /**
     * Sort set.
     *
     * @param userList the user list
     * @return the set
     */
    public Set<User> sort(List<User> userList) {
        TreeSet<User> set = new TreeSet<>();
        for (User user : userList) {
            set.add(user);
        }
        return set;
    }
}
