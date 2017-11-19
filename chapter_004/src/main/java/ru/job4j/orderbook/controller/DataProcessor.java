package ru.job4j.orderbook.controller;

import ru.job4j.orderbook.model.OrderBook;
import ru.job4j.orderbook.model.OrderBookStore;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

/**
 * The type Data processor.
 */
public class DataProcessor {
    /**
     * Store for saving order books.
     */
    private OrderBookStore store;

    /**
     * Instantiates a new Data processor.
     *
     * @param store the store
     */
    public DataProcessor(OrderBookStore store) {
        this.store = store;
    }

    /**
     * Process all books.
     */
    public void processAllBooks() {
        Iterator<OrderBook> it = store.getAllBooks().values().iterator();
        while (it.hasNext()) {
            OrderBook book = it.next();
            collectBidAndAsk(book);
            matchOrders(book);
        }
    }

    /**
     * Collect bid and ask.
     *
     * @param book the book
     */
    public void collectBidAndAsk(OrderBook book) {
        final String buyString = "BUY";
        final String sellString = "SELL";

        TreeMap<Double, Integer> bid = new TreeMap<Double, Integer>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        });
        TreeMap<Double, Integer> ask = new TreeMap<Double, Integer>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o1.compareTo(o2);
            }
        });


        final HashMap<String, OrderBook.Order> orders = book.getOrders();

        if (orders.size() > 0) {
            Iterator<OrderBook.Order> it = book.getOrders().values().iterator();

            while (it.hasNext()) {
                OrderBook.Order order = it.next();

                double price = order.getPrice();
                int volume = order.getVolume();

                if (order.getOrderType().equals(buyString)) {
                    if (bid.containsKey(price)) {
                        bid.put(price, bid.get(price) + volume);
                    } else {
                        bid.put(price, volume);
                    }

                } else if (order.getOrderType().equals(sellString)) {
                    if (ask.containsKey(price)) {
                        ask.put(price, ask.get(price) + volume);
                    } else {
                        ask.put(price, volume);
                    }
                }
            }
        }

        book.setAsk(ask);
        book.setBid(bid);
    }

    /**
     * Match orders.
     *
     * @param book the book
     */
    public void matchOrders(OrderBook book) {
        TreeMap<Double, Integer> bookAsk = book.getAsk();
        TreeMap<Double, Integer> bookBid = book.getBid();

        Iterator<Double> askIterator = bookAsk.keySet().iterator();
        Iterator<Double> bidIterator = bookBid.keySet().iterator();

        while (askIterator.hasNext() & bidIterator.hasNext()) {
            double askPrice = askIterator.next();
            double bidPrice = bidIterator.next();

            if (bidPrice >= askPrice) {
                int askVolume = bookAsk.get(askPrice);
                int bidVolume = bookBid.get(bidPrice);

                if (bidVolume > askVolume) {
                    int diffVolume = bidVolume - askVolume;
                    bookBid.put(bidPrice, diffVolume);
                    askIterator.remove();
                } else if (askVolume > bidVolume) {
                    int diffVolume = askVolume - bidVolume;
                    bookAsk.put(askPrice, diffVolume);
                    bidIterator.remove();
                }
                askIterator = bookAsk.keySet().iterator();
                bidIterator = bookBid.keySet().iterator();
            }
        }
    }
}
