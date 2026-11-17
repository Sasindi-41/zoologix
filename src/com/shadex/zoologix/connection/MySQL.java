
package com.shadex.zoologix.connection;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.Properties;

public class MySQL {

    private static java.sql.Connection connection;

    public static void CreateConnection() throws Exception {

        if (connection == null || connection.isClosed()) {

            Properties properties = new Properties();
            Path configPath = Paths.get("db.properties");

            if (!Files.exists(configPath)) {
                throw new IllegalStateException(
                    "Missing db.properties. Create it in the project root."
                );
            }

            try (InputStream input = Files.newInputStream(configPath)) {
                properties.load(input);
            }

            String url = properties.getProperty("db.url");
            String username = properties.getProperty("db.username");
            String password = properties.getProperty("db.password");

            if (url == null || username == null || password == null) {
                throw new IllegalStateException(
                    "Database settings are incomplete."
                );
            }

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        }
    }

    public static ResultSet executeSearch(String query) throws Exception {
        CreateConnection();
        return connection.createStatement().executeQuery(query);
    }

    public static Integer executeIUD(String query) throws Exception {
        CreateConnection();
        return connection.createStatement().executeUpdate(query);
    }
}
