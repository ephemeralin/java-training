package ru.job4j.exchanger;

/**
 * Created by ephemeralin on 31.07.17.
 */
public class Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws Exception the exception
     */
    public static void main(String[] args) throws Exception {
        int[] availableNominals = ExchangerMachine.AVAILABLE_NOMINALS;
        ExchangerMachine exchangerMachine = new ExchangerMachine(availableNominals);
        int note = ExchangerMachine.NOTE_NOMINAL;

        exchangerMachine.setExchangeMethod(new ExchangerMachine.MethodAll1());
        Coin[] coins = exchangerMachine.changeNote(note);
        //showCoins(coins);
        System.out.println(exchangerMachine.getCoinsAsString(coins));

        exchangerMachine.setExchangeMethod(new ExchangerMachine.MethodTwo5());
        coins = exchangerMachine.changeNote(note);
        System.out.println(exchangerMachine.getCoinsAsString(coins));

        exchangerMachine.setExchangeMethod(new ExchangerMachine.MethodOne5Five1());
        coins = exchangerMachine.changeNote(note);
        System.out.println(exchangerMachine.getCoinsAsString(coins));

    }

    /**
     * Show coins.
     *
     * @param coins the coins
     */
    public static void showCoins(Coin[] coins) {
        for (int i = 0; i < coins.length; i++) {
            System.out.println(coins[i].getName());
        }
    }
}
