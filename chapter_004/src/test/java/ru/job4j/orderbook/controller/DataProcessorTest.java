package ru.job4j.orderbook.controller;

import org.junit.Test;
import ru.job4j.orderbook.model.OrderBook;
import ru.job4j.orderbook.model.OrderBookStore;

import java.util.Comparator;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Data processor test.
 */
public class DataProcessorTest {
    /**
     * Collect bid and ask test.
     *
     * @throws Exception the exception
     */
    @Test
    public void collectBidAndAskTest() throws Exception {
        OrderBookStore store = new OrderBookStore();
        OrderBook book = store.findBook("book 1");

        book.addOrder("143", "SELL", 99.5, 12);
        book.addOrder("154", "BUY", 100.0, 50);
        book.addOrder("148", "BUY", 100.0, 96);
        book.addOrder("149", "SELL", 100.8, 24);
        book.addOrder("150", "SELL", 100.3, 57);
        book.addOrder("151", "BUY", 100.2, 42);
        book.addOrder("152", "BUY", 99.6, 80);
        book.addOrder("142", "BUY", 101.2, 40);
        book.addOrder("153", "SELL", 99.9, 92);

        DataProcessor dataProcessor = new DataProcessor(store);
        dataProcessor.collectBidAndAsk(book);

        TreeMap<Double, Integer> expectedBid = new TreeMap<Double, Integer>() {
            {
                put(101.2, 40);
                put(100.2, 42);
                put(100.0, 146);
                put(99.6, 80);
            }
        };
        TreeMap<Double, Integer> expectedAsk = new TreeMap<Double, Integer>() {
            {
                put(99.5, 12);
                put(99.9, 92);
                put(100.3, 57);
                put(100.8, 24);
            }
        };

        assertThat(book.getBid(), is(expectedBid));
        assertThat(book.getAsk(), is(expectedAsk));
    }

    /**
     * Match orders test.
     *
     * @throws Exception the exception
     */
    @Test
    public void matchOrdersTest() throws Exception {
        OrderBookStore store = new OrderBookStore();
        OrderBook book = store.findBook("book 1");

        TreeMap<Double, Integer> bid = new TreeMap<Double, Integer>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        }) {
            {
                put(101.2, 40);
                put(100.2, 42);
                put(100.0, 146);
                put(99.6, 80);
            }
        };
        book.setBid(bid);

        TreeMap<Double, Integer> ask = new TreeMap<Double, Integer>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        }) {
            {
                put(99.5, 12);
                put(99.9, 92);
                put(100.3, 57);
                put(100.8, 24);
            }
        };
        book.setAsk(ask);

        DataProcessor dataProcessor = new DataProcessor(store);
        dataProcessor.matchOrders(book);

        TreeMap<Double, Integer> expectedBid = new TreeMap<Double, Integer>() {
            {
                put(100.0, 124);
                put(99.60, 80);
            }
        };
        TreeMap<Double, Integer> expectedAsk = new TreeMap<Double, Integer>() {
            {
                put(100.30, 57);
                put(100.80, 24);
            }
        };

        assertThat(book.getBid(), is(expectedBid));
        assertThat(book.getAsk(), is(expectedAsk));
    }

}