package ru.job4j.orderbook.model;

import java.util.Comparator;
import java.util.TreeMap;

/**
 * The type Order book store.
 */
public class OrderBookStore {
    /**
     * Saved order books.
     */
    private TreeMap<String, OrderBook> orderBooks;

    /**
     * Instantiates a new Order book store.
     */
    public OrderBookStore() {
        this.orderBooks = new TreeMap<>(Comparator.naturalOrder());
    }

    /**
     * Find book order book.
     *
     * @param name the name
     * @return the order book
     */
    public OrderBook findBook(String name) {
        OrderBook book = this.orderBooks.get(name);
        if (book == null) {
            book = createBook(name);
        }
        return book;
    }

    /**
     * Create new book in the Store.
     * @param name name of the book
     * @return new book
     */
    private OrderBook createBook(String name) {
        OrderBook newBook = new OrderBook(name);
        orderBooks.put(name, newBook);
        return newBook;
    }

    /**
     * Gets all books.
     *
     * @return the all books
     */
    public TreeMap<String, OrderBook> getAllBooks() {
        return this.orderBooks;
    }
}
