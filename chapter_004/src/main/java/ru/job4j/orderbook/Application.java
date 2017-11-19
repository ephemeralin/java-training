package ru.job4j.orderbook;

import ru.job4j.orderbook.controller.Importer;
import ru.job4j.orderbook.controller.DataProcessor;
import ru.job4j.orderbook.model.OrderBookStore;
import ru.job4j.orderbook.view.DataViewer;

/**
 * The type Application.
 */
public class Application {
    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        long startTime = System.nanoTime();

        String filePath;

        if (args.length > 0) {
            filePath = args[0];
        } else {
            throw new ArrayIndexOutOfBoundsException("No file path!");
        }

        OrderBookStore store = new OrderBookStore();

        //retrievingDataUsingSlowDOM(filePath, store);
        retrievingDataUsingFastStAX(filePath, store);

        DataProcessor dataProcessor = new DataProcessor(store);
        dataProcessor.processAllBooks();

        DataViewer dataViewer = new DataViewer(store);
        System.out.println(dataViewer.getBookStoreViewByText().toString());

        long estimatedTime = System.nanoTime() - startTime;
        System.out.println("Processing has been finished. Used time (s): " + estimatedTime / 1000000000.0);
    }

    /**
     * General method for retrieving data using slow DOM.
     * @param filePath file path
     * @param store store where books are saved
     */
    private static void retrievingDataUsingSlowDOM(String filePath, OrderBookStore store) {
        long startTime = System.nanoTime();

        new Importer().importDataUsingSlowDOM(store, filePath);

        long estimatedTime = System.nanoTime() - startTime;

        System.out.println("Using slow DOM. Time (s): " + estimatedTime / 1000000000.0);
    }

    /**
     * General method for retrieving data using fast StAX.
     * @param filePath file path
     * @param store store where books are saved
     */
    private static void retrievingDataUsingFastStAX(String filePath, OrderBookStore store) {
        long startTime = System.nanoTime();

        new Importer().importDataUsingFastStAX(store, filePath);

        long estimatedTime = System.nanoTime() - startTime;

        System.out.println("Using fast StAX. Time (s): " + estimatedTime / 1000000000.0);
    }
}
