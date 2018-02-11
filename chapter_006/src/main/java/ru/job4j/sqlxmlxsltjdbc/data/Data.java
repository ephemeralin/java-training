package ru.job4j.sqlxmlxsltjdbc.data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Data.
 */
@XmlRootElement(name = "entries")
public class Data implements Serializable {

    /**
     * The Entries.
     */
//@XmlElementWrapper(name = "entries")
    @XmlElement(name = "entry")
    private List<Entry> entries = new ArrayList<Entry>();

    /**
     * Gets entries.
     *
     * @return the entries
     */
    public List<Entry> getEntries() {
        return this.entries;
    }

    /**
     * Add entry.
     *
     * @param entry the entry
     */
    public void addEntry(Entry entry) {
        this.entries.add(entry);
    }
}

