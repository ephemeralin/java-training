package ru.job4j.usersortcomp;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * The type Sort user.
 */
public class SortUser {


    /**
     * Sort by name length list.
     *
     * @param list the list
     * @return the list
     */
    public List<User> sortByNameLength(List<User> list) {

        /**
         * Comparator by name length.
         */
        class CompByNameLenght implements Comparator<User> {
            /**
             * Compare by name length.
             * @param aUser the first user
             * @param bUser the second user
             * @return result
             */
            public int compare(User aUser, User bUser) {
                return aUser.getName().length() - bUser.getName().length();
            }
        }

        Collections.sort(list, new CompByNameLenght());
        return list;
    }

    /**
     * Sort by name than by age list.
     *
     * @param list the list
     * @return the list
     */
    public List<User> sortByNameThanByAge(List<User> list) {

        /**
         * Comparator by name.
         */
        class CompByName implements Comparator<User> {
            /**
             * Compare by name.
             * @param aUser the first user
             * @param bUser the second user
             * @return result
             */
            public int compare(User aUser, User bUser) {
                return aUser.getName().compareTo(bUser.getName());
            }
        }

        /**
         * Comparator by age.
         */
        class CompByAge implements Comparator<User> {
            /**
             * Compare by age.
             * @param aUser the first user
             * @param bUser the second user
             * @return result
             */
            public int compare(User aUser, User bUser) {
                return Integer.compare(aUser.getAge(), bUser.getAge());
            }
        }

        Collections.sort(list, new CompByName().thenComparing(new CompByAge()));
        return list;
    }
}
