package project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet for deleting a book from the library.
 */
@WebServlet("/eliminar")
public class DeleteBookServlet extends HttpServlet {
    private BookService bookService = new BookService();

    /**
     * Handles GET requests to delete a book.
     * @param req The HTTP request.
     * @param resp The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        bookService.deleteBook(id);
        resp.sendRedirect("llibreria");
    }
}