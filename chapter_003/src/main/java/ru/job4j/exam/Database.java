package ru.job4j.exam;


import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

/**
 * Created by ephemeralin on 17.08.17.
 */
public class Database {

    /**
     * Instantiates a new Database.
     *
     * @param data the data
     */
    public Database(TreeMap<User, List<Account>> data) {
        this.data = data;
    }

    /**
     * Entity of the Database.
     */
    private TreeMap<User, List<Account>> data;

    /**
     * Add user.
     *
     * @param user the user
     */
    public void addUser(User user) {
        data.put(user, new ArrayList<>());
    }

    /**
     * Delete user.
     *
     * @param user the user
     */
    public void deleteUser(User user) {
        data.remove(user);
    }

    /**
     * Add account to user.
     *
     * @param user    the user
     * @param account the account
     */
    public void addAccountToUser(User user, Account account) {
        List<Account> accounts = data.get(user);
        accounts.add(account);
        data.replace(user, accounts);
    }

    /**
     * Delete account from user.
     *
     * @param user    the user
     * @param account the account
     */
    public void deleteAccountFromUser(User user, Account account) {
        List<Account> accounts = data.get(user);
        accounts.remove(account);
    }

    /**
     * Get all users.
     *
     * @return all users
     */
    public User[] getUsers() {
        return data.keySet().toArray(new User[data.size()]);
    }

    /**
     * Gets user accounts.
     *
     * @param user the user
     * @return the user accounts
     */
    public List<Account> getUserAccounts(User user) {
        return data.get(user);
    }

    /**
     * Transfer money boolean.
     *
     * @param srcUser    the source user
     * @param srcAccount the source account
     * @param dstUser    the destination user
     * @param dstAccount the destination account
     * @param amount     the amount
     * @return the boolean
     */
    public boolean transferMoney(User srcUser, Account srcAccount, User dstUser, Account dstAccount, double amount) {
        boolean isOk = false;
        if (this.getUserAccounts(srcUser).contains(srcAccount) && (srcUser.equals(dstUser) || this.getUserAccounts(dstUser).contains(dstAccount))) {
            if (srcAccount.getValue() >= amount) {
                srcAccount.setValue(srcAccount.getValue() - amount);
                dstAccount.setValue(dstAccount.getValue() + amount);
                isOk = true;
            }
        }
        return isOk;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public TreeMap<User, List<Account>> getData() {
        return data;
    }
}
