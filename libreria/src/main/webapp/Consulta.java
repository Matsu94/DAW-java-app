package com.example.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import project.Connexio;

public class Consulta {

    /**
     * Retrieves all books from the database.
     * @return A list of book titles and their IDs.
     */
    public List<String> getAllBooks() {
        List<String> books = new ArrayList<>();
        String query = "SELECT id, titol FROM llibres";
        try (Connection conn = Connexio.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                books.add(rs.getInt("id") + " - " + rs.getString("titol"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Retrieves books by genre.
     * @param genreId The ID of the genre.
     * @return A list of books in the specified genre.
     */
    public List<String> getBooksByGenre(int genreId) {
        List<String> books = new ArrayList<>();
        String query = "SELECT l.* FROM llibres l JOIN llibre_genere lg ON l.id = lg.id_llibre WHERE lg.id_genere = ?";
        try (Connection conn = Connexio.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, genreId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    books.add(rs.getString("titol") + " (" + rs.getString("isbn") + ")");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    // Add similar methods for authors, editorials, etc.
}