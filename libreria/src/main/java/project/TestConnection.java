package project;

import java.sql.Connection;

public class TestConnection {
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