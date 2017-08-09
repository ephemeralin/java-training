package ru.job4j.exchanger;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by ephemeralin on 01.08.17.
 */
public class MethodOne5Five1Test {
    /**
     * Change.
     *
     * @throws Exception the exception
     */
    @Test
    public void change() throws Exception {
        int[] availableNominals = ExchangerMachine.AVAILABLE_NOMINALS;
        int note = ExchangerMachine.NOTE_NOMINAL;
        ExchangerMachine exchangerMachine = new ExchangerMachine(availableNominals);

        exchangerMachine.setExchangeMethod(new ExchangerMachine.MethodOne5Five1());
        Coin[] coins = exchangerMachine.changeNote(note);

        assertThat(exchangerMachine.getCoinsAsString(coins), is("5 rub, 1 rub, 1 rub, 1 rub, 1 rub, 1 rub"));
    }

}