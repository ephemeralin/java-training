package ru.job4j.orderbook.controller;

import ru.job4j.orderbook.model.OrderBook;
import ru.job4j.orderbook.model.OrderBookStore;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * The type Importer.
 */
public class Importer {

    /**
     * Import data using slow dom.
     *
     * @param store    the store
     * @param fileName the file name
     */
    public void importDataUsingSlowDOM(OrderBookStore store, String fileName) {

        try {

            DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
            DocumentBuilder db = dbf.newDocumentBuilder();

            Document document = db.parse(new File(fileName));

            Node root = document.getDocumentElement();

            NodeList nodes = root.getChildNodes();

            for (int i = 0; i < nodes.getLength(); i++) {
                Node node = nodes.item(i);
                if (!node.hasAttributes()) {
                    continue;
                }
                NamedNodeMap attributes = node.getAttributes();
                String editOrderType = node.getNodeName();

                String bookName = attributes.getNamedItem("book").getNodeValue();
                OrderBook book = store.findBook(bookName);

                if (editOrderType.equals("AddOrder")) {
                    book.addOrder(
                            attributes.getNamedItem("orderId").getNodeValue(),
                            attributes.getNamedItem("operation").getNodeValue(),
                            Double.valueOf(attributes.getNamedItem("price").getNodeValue()),
                            Integer.valueOf(attributes.getNamedItem("volume").getNodeValue())
                    );
                } else if (editOrderType.equals("DeleteOrder")) {
                    book.deleteOrder(attributes.getNamedItem("orderId").getNodeValue());

                } else {
                    throw new IOException("Unsupported edit order type " + editOrderType);
                }
            }

        } catch (ParserConfigurationException ex) {
            ex.printStackTrace(System.out);
        } catch (SAXException ex) {
            ex.printStackTrace(System.out);
        } catch (IOException ex) {
            ex.printStackTrace(System.out);
        }
        String s = "";
    }


    /**
     * Import data using fast st ax.
     *
     * @param store    the store
     * @param fileName the file name
     */
    public void importDataUsingFastStAX(OrderBookStore store, String fileName) {
        try {
            XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(fileName, new FileInputStream(fileName));

            while (xmlr.hasNext()) {
                xmlr.next();
                if (xmlr.isStartElement()) {

                    String editOrderType = xmlr.getLocalName();
                    if (editOrderType == "AddOrder") {
                        String bookName = xmlr.getAttributeValue(null, "book");
                        OrderBook book = store.findBook(bookName);
                        book.addOrder(
                                xmlr.getAttributeValue(null, "orderId"),
                                xmlr.getAttributeValue(null, "operation"),
                                Double.valueOf(xmlr.getAttributeValue(null, "price")),
                                Integer.valueOf(xmlr.getAttributeValue(null, "volume"))
                        );
                    } else if (editOrderType == "DeleteOrder") {
                        String bookName = xmlr.getAttributeValue(null, "book");
                        OrderBook book = store.findBook(bookName);
                        book.deleteOrder(xmlr.getAttributeValue(null, "orderId"));

                    } else if (editOrderType != "Orders") {
                        throw new IOException("Unsupported edit order type " + editOrderType);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException ex) {
            ex.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
