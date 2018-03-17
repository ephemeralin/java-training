package ru.job4j.sqlxmlxsltjdbc.util;

import ru.job4j.sqlxmlxsltjdbc.data.Data;
import ru.job4j.sqlxmlxsltjdbc.data.Entry;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.*;
import java.sql.*;
import java.util.List;

/**
 * The type Data processor.
 */
public class DataProcessor {
    /**
     * Default number of entries.
     */
    private int numberOfEntries = 10;
    /**
     * Database connection.
     */
    private DatabaseConnection databaseConnection;

    /**
     * Dataprocessor constructor.
     * @param dbName database name
     */
    public DataProcessor(String dbName) {
        this.databaseConnection = new DatabaseConnection(dbName);
    }

    /**
     * Sets number of entries.
     *
     * @param numberOfEntries the number of entries
     */
    public void setNumberOfEntries(int numberOfEntries) {
        this.numberOfEntries = numberOfEntries;
    }

    /**
     * Generate data data.
     *
     * @return the data
     */
    public Data generateData() {
        Data data = new Data();
        for (int i = 1; i <= numberOfEntries; i++) {
            Entry entry = new Entry();
            entry.setField(i);
            data.addEntry(entry);
        }
        return  data;
    }

    /**
     * Put data into db.
     * @param data   the data
     * @throws SQLException the sql exception
     */
    public void putDataIntoDb(Data data) {
        try (Connection conn = databaseConnection.getConnection()) {
            if (databaseConnection.prepareDatabaseStructure("CREATE TABLE IF NOT EXISTS data (id INTEGER PRIMARY KEY);")
                    && databaseConnection.prepareDatabaseStructure("DELETE FROM data;")) {
                System.out.println("- Put data into DB... ");
                conn.setAutoCommit(false);
                PreparedStatement pstmt = conn.prepareStatement("INSERT INTO data VALUES(?)");
                int i = 0;
                List<Entry> entries = data.getEntries();
                for (Entry entry : entries) {
                    pstmt.setInt(1, entry.getField());
                    pstmt.addBatch();
                    if (i % 1000 == 0 || i == entries.size()) {
                        pstmt.executeBatch();
                    }
                }
                conn.commit();
                conn.setAutoCommit(true);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("- Data has been put in DB.");
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Gets data from sql.
     * @return the data from sql
     */
    public Data getDataFromSql() {
        Data data = new Data();
        try (Connection conn = databaseConnection.getConnection()) {
            System.out.println("- Receiving data from DB... ");
            String query = "SELECT * FROM data";
            try (Statement stmt = conn.createStatement()) {
                ResultSet rs = stmt.executeQuery(query);
                while (rs.next()) {
                    int field = rs.getInt("id");
                    Entry entry = new Entry();
                    entry.setField(field);
                    data.addEntry(entry);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("- Data has been received from DB.");
        System.out.println(new Timestamp(System.currentTimeMillis()));
        return data;
    }

    /**
     * Put data in xml file.
     *
     * @param data     the data
     * @param fileName the file name
     * @throws JAXBException the jaxb exception
     * @throws IOException   the io exception
     */
    public void putDataInXmlFile(Data data, String fileName) throws JAXBException, IOException {
        System.out.println("- Marshall data in xml file... ");
        JAXBContext context = JAXBContext.newInstance(Data.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(data, new FileWriter(fileName));
        System.out.println("- Data has been marshalled. ");
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Convert xml file.
     *
     * @param stylesheetFileName the stylesheet file name
     * @param sourceFileName     the source file name
     * @param resultFileName     the result file name
     * @throws TransformerException the transformer exception
     */
    public void convertXmlFile(String stylesheetFileName, String sourceFileName, String resultFileName) throws TransformerException {
        System.out.println("- Convert xml file... ");
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File(stylesheetFileName));
        Transformer transformer = factory.newTransformer(xslt);
        Source text = new StreamSource(new File(sourceFileName));
        transformer.transform(text, new StreamResult(new File(resultFileName)));
        System.out.println("- Data has been converted. ");
        System.out.println(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * Gets data from xml file.
     *
     * @param sourcefileName the sourcefile name
     * @return the data from xml file
     * @throws FileNotFoundException the file not found exception
     * @throws XMLStreamException    the xml stream exception
     */
    public Data getDataFromXmlFile(String sourcefileName) throws FileNotFoundException, XMLStreamException {
        System.out.println("- Receiving data from xml file... ");
        Data data = new Data();
        XMLStreamReader xmlr = XMLInputFactory.newInstance().createXMLStreamReader(sourcefileName, new FileInputStream(sourcefileName));
        while (xmlr.hasNext()) {
            xmlr.next();
            if (xmlr.isStartElement()) {
                if (xmlr.getLocalName() == "entry") {
                    Entry entry = new Entry();
                    int field = Integer.parseInt(xmlr.getAttributeValue(0));
                    entry.setField(field);
                    data.addEntry(entry);
                }
            }
        }
        System.out.println("- Data has been received. ");
        System.out.println(new Timestamp(System.currentTimeMillis()));
        return data;
    }

    /**
     * Calculate sum of all fields int.
     *
     * @param data the data
     * @return the int
     */
    public int calculateSumOfAllFields(Data data) {
        System.out.println("- Calculating sum of all elements... ");
        int sum = 0;
        if (data != null) {
            for (Entry entry : data.getEntries()) {
                sum = sum + entry.getField();
            }
        }
        System.out.println("- Calculating has been finished. ");
        System.out.println(new Timestamp(System.currentTimeMillis()));
        return sum;
    }
}
