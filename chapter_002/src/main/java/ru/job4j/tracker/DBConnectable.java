package ru.job4j.tracker;

import java.sql.Connection;
import java.util.Properties;

public interface DBConnectable {
    Connection getConnection(Properties properties);
    void prepareTables();
}
