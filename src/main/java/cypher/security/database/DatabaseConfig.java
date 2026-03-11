package cypher.security.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConfig {
    private static final String URL = "jdbc:sqlite:vendas:db";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }
}