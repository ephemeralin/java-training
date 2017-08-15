package ru.job4j.listmapconvert;

import java.util.HashMap;
import java.util.List;

/**
 * The type User convert.
 */
public class UserConvert {
    /**
     * Process hash map.
     *
     * @param list the list
     * @return the hash map
     */
    public HashMap<Integer, User> process(List<User> list) {
        HashMap<Integer, User> hashMap = new HashMap<>();

        for (User user : list) {
            hashMap.put(user.getId(), user);
        }

        return hashMap;
    }
}
