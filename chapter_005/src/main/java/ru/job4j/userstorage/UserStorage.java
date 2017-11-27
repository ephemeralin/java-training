package ru.job4j.userstorage;

import net.jcip.annotations.GuardedBy;
import net.jcip.annotations.ThreadSafe;

import java.util.HashMap;

/**
 * The type User storage.
 */
@ThreadSafe
public class UserStorage {

    /**
     * The Lock.
     */
    private Object lock = new Object();

    /**
     * The Storage.
     */
    @GuardedBy("lock")
    private HashMap<Integer, User> store;

    /**
     * Constructor.
     */
    private UserStorage() {
        HashMap<Integer, User> store = new HashMap<>();
    }

    /**
     * Add user.
     *
     * @param user the user
     * @return the user
     */
    public User add(User user) {
        synchronized (lock) {
            return this.store.put(user.getId(), user);
        }
    }

    /**
     * Update boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean update(User user) {
        synchronized (lock) {
            return this.update(user);
        }
    }

    /**
     * Delete boolean.
     *
     * @param user the user
     * @return the boolean
     */
    public boolean delete(User user) {
        synchronized (lock) {
            return this.delete(user);
        }
    }

    /**
     * Transfer.
     *
     * @param fromId the from id
     * @param toId   the to id
     * @param amount the amount
     * @throws Exception the exception
     */
    public void transfer(int fromId, int toId, int amount) throws Exception {
        synchronized (lock) {
            User fromUser = store.get(fromId);
            User toUser = store.get(toId);
            int fromUserAmount = fromUser.getAmount();
            if (fromUserAmount < amount) {
                throw new Exception("Not enough money to transfer!");
            }
            fromUser.setAmount(fromUserAmount - amount);
            toUser.setAmount(toUser.getAmount() + amount);
        }
    }
}
