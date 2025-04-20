package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseContext {
    private String propertiesFile;

    /**
     * @param propertiesFile path to db.properties (e.g. "db.properties" or "resources/db.properties")
     */
    public DatabaseContext(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    /**
     * Loads JDBC settings from properties file and returns a Connection.
     */
    public Connection getConnection() throws IOException, SQLException {
        // 1. Load the properties
        Properties props = new Properties();
        try (FileInputStream in = new FileInputStream(propertiesFile)) {
            props.load(in);
        }

        // 2. Read individual settings
        String url      = props.getProperty("db.url");
        String user     = props.getProperty("db.user");
        String password = props.getProperty("db.password");

        // 3. Return a live JDBC connection
        return DriverManager.getConnection(url, user, password);
    }
}
