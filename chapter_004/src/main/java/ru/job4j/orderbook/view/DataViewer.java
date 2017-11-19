package ru.job4j.orderbook.view;

import ru.job4j.orderbook.model.OrderBook;
import ru.job4j.orderbook.model.OrderBookStore;

import java.util.Iterator;
import java.util.TreeMap;

/**
 * The type Data viewer.
 */
public class DataViewer {
    /**
     * The Store.
     */
    private OrderBookStore store;

    /**
     * Instantiates a new Data viewer.
     *
     * @param store the store
     */
    public DataViewer(OrderBookStore store) {
        this.store = store;
    }

    /**
     * Gets book store view by text.
     *
     * @return the book store view by text
     */
    public StringBuilder getBookStoreViewByText() {
        StringBuilder stringBuilder = new StringBuilder();

        Iterator<OrderBook> it = store.getAllBooks().values().iterator();
        while (it.hasNext()) {
            stringBuilder.append("-------------------------------").append(System.lineSeparator());
            OrderBook book = it.next();
            stringBuilder.append(book.getName()).append(System.lineSeparator());

            TreeMap<Double, Integer> bookAsk = book.getAsk();
            TreeMap<Double, Integer> bookBid = book.getBid();

            Iterator<Double> askIterator = bookAsk.keySet().iterator();
            Iterator<Double> bidIterator = bookBid.keySet().iterator();

            boolean hasDataBid = true;
            boolean hasDataAsk = true;
            while (hasDataBid || hasDataAsk) {
                String newLine;
                if (bidIterator.hasNext()) {
                    double bidPrice = bidIterator.next();
                    int bidVolume = bookBid.get(bidPrice);
                    newLine = String.format("%d@%s   - ", bidVolume, String.format("%.2f", bidPrice));
                    hasDataBid = true;
                } else {
                    newLine = "-----   -";
                    hasDataBid = false;
                }

                if (askIterator.hasNext()) {
                    double askPrice = askIterator.next();
                    int askVolume = bookAsk.get(askPrice);
                    String askLine = String.format("%d@%s", askVolume, String.format("%.2f", askPrice));
                    newLine = String.format("%s   %s", newLine, askLine);
                    hasDataAsk = true;
                } else {
                    newLine = String.format("%s   %s", newLine, "-----");
                    hasDataAsk = false;
                }
                if (hasDataBid || hasDataAsk) {
                    stringBuilder.append(newLine).append(System.lineSeparator());
                }
            }
        }
        return stringBuilder;
    }
}
