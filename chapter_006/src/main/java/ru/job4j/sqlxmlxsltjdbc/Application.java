package ru.job4j.sqlxmlxsltjdbc;

import ru.job4j.sqlxmlxsltjdbc.data.Data;
import ru.job4j.sqlxmlxsltjdbc.util.DataProcessor;

import javax.xml.bind.JAXBException;
import javax.xml.stream.XMLStreamException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * The type Application.
 */
public class Application {
    /**
     * The constant PATH.
     */
    public static final String PATH = "chapter_006/src/main/java/ru/job4j/sqlxmlxsltjdbc/resources/";

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     * @throws JAXBException        the jaxb exception
     * @throws IOException          the io exception
     * @throws SQLException         the sql exception
     * @throws TransformerException the transformer exception
     * @throws XMLStreamException   the xml stream exception
     */
    public static void main(String[] args) throws JAXBException, IOException, SQLException, TransformerException, XMLStreamException {
        String dbName = args[0];
        int numberOfEntries = Integer.parseInt(args[1]);
        System.out.println("--- Start program. Number of entries: " + numberOfEntries);
        System.out.println(new Timestamp(System.currentTimeMillis()));
        DataProcessor dataProcessor = new DataProcessor();
        dataProcessor.setNumberOfEntries(numberOfEntries);
        Data data = dataProcessor.generateData();
        dataProcessor.putDataIntoDb(dbName, data);
        data = dataProcessor.getDataFromSql(dbName);
        dataProcessor.putDataInXmlFile(data, PATH + "1.xml");
        dataProcessor.convertXmlFile(PATH + "xml_convert.xsl",
                                        PATH +  "1.xml", PATH + "2.xml");
        data = dataProcessor.getDataFromXmlFile(PATH + "2.xml");
        int amount = dataProcessor.calculateSumOfAllFields(data);
        System.out.println("Processing result: " + amount);
        System.out.println("--- Finish program.");
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }
}
