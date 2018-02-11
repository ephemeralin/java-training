package ru.job4j.sqlxmlxsltjdbc.data;

import java.io.Serializable;
import java.util.Objects;

/**
 * The type Entry.
 */
public class Entry implements Serializable {

    /**
     * Field of entry.
     */
    private int field;

    /**
     * Sets field.
     *
     * @param field the field
     */
    public void setField(int field) {
        this.field = field;
    }

    /**
     * Gets field.
     *
     * @return the field
     */
    public int getField() {
        return field;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Entry entry = (Entry) o;
        return getField() == entry.getField();
    }

    @Override
    public int hashCode() {

        return Objects.hash(getField());
    }

    @Override
    public String toString() {
        return "Entry{"
                + "field=" + field
                + '}';
    }
}
