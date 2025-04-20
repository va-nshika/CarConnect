package util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseContext {
    private String propertiesFile;

    public DatabaseContext(String propertiesFile) {
        this.propertiesFile = propertiesFile;
    }

    public Connection getConnection() throws IOException, SQLException {
        // Reads "db.url", "db.user", "db.password" from your properties file
        String connString = DBPropertyUtil.getConnectionString(propertiesFile);
        return DBConnUtil.getConnection(connString);
    }
}
