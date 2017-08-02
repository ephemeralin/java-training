package ru.job4j.exchanger;

/**
 * Created by ephemeralin on 31.07.17.
 */
public interface ExchangeMethod {
    /**
     * Change coin [ ].
     *
     * @param exchangerMachine the exchanger machine
     * @param noteNominal      the note nominal
     * @return the coin [ ]
     * @throws Exception the exception
     */
    Coin[] change(ExchangerMachine exchangerMachine, int noteNominal) throws Exception;
}
