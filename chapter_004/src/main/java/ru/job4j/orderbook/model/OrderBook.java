package ru.job4j.orderbook.model;
import java.util.HashMap;
import java.util.TreeMap;


/**
 * The type Order book.
 */
public class OrderBook {
    /**
     * Name of the book.
     */
    private String name;

    /**
     * Orders.
     */
    private HashMap<String, Order> orders;
    /**
     * Bids.
     */
    private TreeMap<Double, Integer> bid;
    /**
     * Asks.
     */
    private TreeMap<Double, Integer> ask;

    /**
     * Instantiates a new Order book.
     *
     * @param name the name
     */
    public OrderBook(String name) {
        this.name = name;
        this.orders = new HashMap<>();
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * The type Order.
     */
    public static class Order {
        /**
         * Order type.
         */
        private String orderType;
        /**
         * Price.
         */
        private double price;
        /**
         * Volume.
         */
        private int volume;

        /**
         * Instantiates a new Order.
         *
         * @param orderType the order type
         * @param price     the price
         * @param volume    the volume
         */
        public Order(String orderType, double price, int volume) {
            this.orderType = orderType;
            this.price = price;
            this.volume = volume;
        }

        /**
         * Gets order type.
         *
         * @return the order type
         */
        public String getOrderType() {
            return orderType;
        }

        /**
         * Gets price.
         *
         * @return the price
         */
        public double getPrice() {
            return price;
        }

        /**
         * Gets volume.
         *
         * @return the volume
         */
        public int getVolume() {
            return volume;
        }
    }

    /**
     * Gets orders.
     *
     * @return the orders
     */
    public HashMap<String, Order> getOrders() {
        return orders;
    }

    /**
     * Sets bid.
     *
     * @param bid the bid
     */
    public void setBid(TreeMap<Double, Integer> bid) {
        this.bid = bid;
    }

    /**
     * Sets ask.
     *
     * @param ask the ask
     */
    public void setAsk(TreeMap<Double, Integer> ask) {
        this.ask = ask;
    }

    /**
     * Gets bid.
     *
     * @return the bid
     */
    public TreeMap<Double, Integer> getBid() {
        return bid;

    }

    /**
     * Gets ask.
     *
     * @return the ask
     */
    public TreeMap<Double, Integer> getAsk() {
        return ask;
    }

    /**
     * Add order order.
     *
     * @param orderId   the order id
     * @param operation the operation
     * @param price     the price
     * @param volume    the volume
     * @return the order
     */
    public Order addOrder(String orderId, String operation, double price, int volume) {
        return orders.put(orderId, new Order(operation, price, volume));
    }

    /**
     * Delete order order.
     *
     * @param orderId the order id
     * @return the order
     */
    public Order deleteOrder(String orderId) {
        return orders.remove(orderId);
    }
}