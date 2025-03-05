package project;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Manages database connections for the application.
 */
public class Connexio {

    // Database credentials
    private static final String URL = "jdbc:mariadb://192.168.56.94:3306/llibres";
    private static final String USER = "omni";
    private static final String PASSWORD = "asd";

    /**
     * Establishes a connection to the database.
     * @return A Connection object.
     * @throws SQLException If a database access error occurs.
     */
    public static Connection getConnection() throws SQLException {
        try {
            // Register the MariaDB JDBC driver (optional with modern JDBC)
            Class.forName("org.mariadb.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException("MariaDB Driver not found!", e);
        }
        // Return the connection
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}