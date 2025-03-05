package project;

import java.sql.Connection;

/**
 * Tests the database connection.
 */
public class TestConnection {
    /**
     * Main method to test the database connection.
     * @param args Command-line arguments (not used).
     */
    public static void main(String[] args) {
        try (Connection conn = Connexio.getConnection()) {
            if (conn != null) {
                System.out.println("Connection established successfully!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}