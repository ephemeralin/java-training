package ru.job4j.producerconsumer;

/**
 * The type Data message.
 */
public class DataMessage {

    /**
     * Some data.
     */
    private String data;

    /**
     * Instantiates a new Data message.
     *
     * @param string the string
     */
    public DataMessage(String string) {
        this.data = string;
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public String getData() {
        return data;
    }
}
