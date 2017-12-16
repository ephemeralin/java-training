package ru.job4j.producerconsumer.blockingqueue;

/**
 * The interface Blockable queue.
 *
 * @param <E> the type parameter
 */
public interface BlockableQ<E> {
    /**
     * Put element.
     *
     * @param e the e
     * @throws InterruptedException the interrupted exception
     */
    void put(E e) throws InterruptedException;

    /**
     * Take element.
     *
     * @return the e
     * @throws InterruptedException the interrupted exception
     */
    E take() throws InterruptedException;
}
