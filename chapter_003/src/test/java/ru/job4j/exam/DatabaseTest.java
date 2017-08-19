package ru.job4j.exam;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ephemeralin on 17.08.17.
 */
public class DatabaseTest {
    /**
     * Add user.
     *
     * @throws Exception the exception
     */
    @Test
    public void addUser() throws Exception {
        String expectedString = "[kevin (4628348912), natasha (9521763908)]";

        Database db = new Database(new TreeMap<User, List<Account>>());
        db.addUser(new User("kevin", "4628348912"));
        db.addUser(new User("natasha", "9521763908"));

        assertThat(Arrays.deepToString(db.getUsers()), is(expectedString));
    }

    /**
     * Delete user.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteUser() throws Exception {
        String expectedString = "[kevin (4628348912), natasha (9521763908)]";

        Database db = new Database(new TreeMap<User, List<Account>>());
        db.addUser(new User("kevin", "4628348912"));
        db.addUser(new User("natasha", "9521763908"));
        User user = new User("jonh", "7352746188");
        db.addUser(user);

        db.deleteUser(user);

        assertThat(Arrays.deepToString(db.getUsers()), is(expectedString));
    }

    /**
     * Add account to user.
     *
     * @throws Exception the exception
     */
    @Test
    public void addAccountToUser() throws Exception {
        Database db = new Database(new TreeMap<User, List<Account>>());
        User user = new User("jonh", "7352746188");
        db.addUser(user);

        Account account = new Account(40000, "4126341625126731723");

        db.addAccountToUser(user, account);

        assertThat(db.getData().get(user).get(0), is(account));
    }

    /**
     * Delete account from user.
     *
     * @throws Exception the exception
     */
    @Test
    public void deleteAccountFromUser() throws Exception {
        Database db = new Database(new TreeMap<User, List<Account>>());
        User user = new User("jonh", "7352746188");
        db.addUser(user);

        Account account = new Account(40000, "4126341625126731723");
        db.addAccountToUser(user, account);
        db.deleteAccountFromUser(user, account);

        assertThat(db.getData().get(user).size(), is(0));
    }

    /**
     * Gets user accounts.
     *
     * @throws Exception the exception
     */
    @Test
    public void getUserAccounts() throws Exception {
        Database db = new Database(new TreeMap<User, List<Account>>());
        User user = new User("jonh", "7352746188");
        db.addUser(user);

        List<Account> accountList = new ArrayList<>();
        Account account = new Account(40000, "4126341625126731723");
        db.addAccountToUser(user, account);
        accountList.add(account);

        account = new Account(2000000, "65384658236482735623");
        db.addAccountToUser(user, account);
        accountList.add(account);

        assertThat(db.getUserAccounts(user).toString(), is(accountList.toString()));
    }

    /**
     * Transfer money.
     *
     * @throws Exception the exception
     */
    @Test
    public void transferMoney() throws Exception {
        Database db = new Database(new TreeMap<User, List<Account>>());
        User firstUser = new User("jonh", "7352746188");
        db.addUser(firstUser);
        Account firstAccount = new Account(40000, "4126341625126731723");
        db.addAccountToUser(firstUser, firstAccount);

        User secondUser = new User("natasha", "9521763908");
        db.addUser(secondUser);
        Account secondAccount = new Account(200000, "65384658236482735623");
        db.addAccountToUser(secondUser, secondAccount);

        assertThat(db.transferMoney(firstUser, firstAccount, secondUser, secondAccount, 30000), is(true));
        assertThat(db.getUserAccounts(firstUser).get(0).getValue(), is(Double.valueOf(10000)));
        assertThat(db.getUserAccounts(secondUser).get(0).getValue(), is(Double.valueOf(230000)));
    }

}