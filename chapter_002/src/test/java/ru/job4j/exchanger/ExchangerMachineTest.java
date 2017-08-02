package ru.job4j.exchanger;

import org.junit.Test;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ephemeralin on 01.08.17.
 */
public class ExchangerMachineTest {

    /**
     * Gets coin 5.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCoin5() throws Exception {
        int[] availableNominals = ExchangerMachine.AVAILABLE_NOMINALS;
        int note = ExchangerMachine.NOTE_NOMINAL;
        ExchangerMachine exchangerMachine = new ExchangerMachine(availableNominals);

        Coin coin = exchangerMachine.getCoin(5);
        assertThat(coin.getName(), is("5 rub"));
    }

    /**
     * Gets coins as string.
     *
     * @throws Exception the exception
     */
    @Test
    public void getCoinsAsString() throws Exception {
        int[] availableNominals = ExchangerMachine.AVAILABLE_NOMINALS;
        int note = ExchangerMachine.NOTE_NOMINAL;
        ExchangerMachine exchangerMachine = new ExchangerMachine(availableNominals);

        Coin[] coins = new Coin[3];
        coins[0] = exchangerMachine.getCoin(5);
        coins[1] = exchangerMachine.getCoin(1);
        coins[2] = exchangerMachine.getCoin(10);
        assertThat(exchangerMachine.getCoinsAsString(coins), is("5 rub, 1 rub, 10 rub"));
    }

}