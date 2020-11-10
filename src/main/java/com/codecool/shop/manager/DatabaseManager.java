package com.codecool.shop.manager;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseManager {

    private static String properties = null;

    public static void setProperties(String properties) {
        DatabaseManager.properties = properties;
    }

    public static Connection connect() throws SQLException, IOException {

        try (InputStream input = DatabaseManager.class.getClassLoader().getResourceAsStream(properties)) {

            Properties properties = new Properties();
            properties.load(input);

            String url = String.format("jdbc:postgresql://%s:%s/%s?user=%s&password=%s",
                    properties.getProperty("host"),
                    properties.getProperty("port"),
                    properties.getProperty("database"),
                    properties.getProperty("user"),
                    properties.getProperty("password")
            );

            return DriverManager.getConnection(url);
        }
    }
}
