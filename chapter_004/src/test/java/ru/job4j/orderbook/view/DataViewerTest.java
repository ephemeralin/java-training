package ru.job4j.orderbook.view;

import org.junit.Test;
import ru.job4j.orderbook.model.OrderBook;
import ru.job4j.orderbook.model.OrderBookStore;

import java.util.Comparator;
import java.util.TreeMap;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * The type Data viewer test.
 */
public class DataViewerTest {
    /**
     * Display book store by console.
     *
     * @throws Exception the exception
     */
    @Test
    public void displayBookStoreByConsole() throws Exception {
        OrderBookStore store = new OrderBookStore();
        OrderBook book = store.findBook("book 1");

        TreeMap<Double, Integer> bid = new TreeMap<Double, Integer>(new Comparator<Double>() {
            @Override
            public int compare(Double o1, Double o2) {
                return o2.compareTo(o1);
            }
        }) {
            {
                put(100.0, 124);
                put(99.60, 80);
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
                put(100.30, 57);
                put(100.80, 24);
            }
        };
        book.setAsk(ask);

        DataViewer dataViewer = new DataViewer(store);
        String resultString = dataViewer.getBookStoreViewByText().toString();

        String expectedString = "-------------------------------\nbook 1\n124@100.00   -    57@100.30"
                + "\n80@99.60   -    24@100.80\n";

        assertThat(resultString, is(expectedString));
    }

}