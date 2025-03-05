package project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides services for managing books in the library system.
 */
public class BookService {

    /**
     * Retrieves all books from the database.
     * @return A list of books.
     */
    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        String query = "SELECT id, titol, isbn, any_publicacio, id_editorial FROM llibres";
        try (Connection conn = Connexio.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                books.add(new Book(
                    rs.getInt("id"),
                    rs.getString("titol"),
                    rs.getString("isbn"),
                    rs.getInt("any_publicacio"),
                    rs.getInt("id_editorial")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return books;
    }

    /**
     * Inserts a new book into the database.
     * @param book The book to insert.
     */
    public void insertBook(Book book) {
        String query = "INSERT INTO llibres (titol, isbn, any_publicacio, id_editorial) VALUES (?, ?, ?, ?)";
        try (Connection conn = Connexio.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitol());
            stmt.setString(2, book.getIsbn());
            stmt.setInt(3, book.getAnyPublicacio());
            stmt.setInt(4, book.getIdEditorial());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Updates an existing book in the database.
     * @param book The book to update.
     */
    public void updateBook(Book book) {
        String query = "UPDATE llibres SET titol = ?, isbn = ?, any_publicacio = ?, id_editorial = ? WHERE id = ?";
        try (Connection conn = Connexio.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, book.getTitol());
            stmt.setString(2, book.getIsbn());
            stmt.setInt(3, book.getAnyPublicacio());
            stmt.setInt(4, book.getIdEditorial());
            stmt.setInt(5, book.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a book from the database.
     * @param id The ID of the book to delete.
     */
    public void deleteBook(int id) {
        String query = "DELETE FROM llibres WHERE id = ?";
        try (Connection conn = Connexio.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}