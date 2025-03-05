package project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

/**
 * Servlet for listing all books in the library.
 */
@WebServlet("/llibreria")
public class ListBooksServlet extends HttpServlet {
    private BookService bookService = new BookService();

    /**
     * Handles GET requests to display the list of books.
     * @param req The HTTP request.
     * @param resp The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Book> books = bookService.getAllBooks();
        req.setAttribute("books", books);
        req.getRequestDispatcher("/llibreria.jsp").forward(req, resp);
    }
}