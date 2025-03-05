package project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

/**
 * Servlet for inserting a new book into the library.
 */
@WebServlet("/insertar")
public class InsertBookServlet extends HttpServlet {
    private BookService bookService = new BookService();

    /**
     * Handles POST requests to insert a new book.
     * @param req The HTTP request.
     * @param resp The HTTP response.
     * @throws ServletException If a servlet-specific error occurs.
     * @throws IOException If an I/O error occurs.
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Book book = new Book();
        book.setTitol(req.getParameter("titol"));
        book.setIsbn(req.getParameter("isbn"));
        book.setAnyPublicacio(Integer.parseInt(req.getParameter("any_publicacio")));
        book.setIdEditorial(Integer.parseInt(req.getParameter("id_editorial")));

        bookService.insertBook(book);
        resp.sendRedirect("llibreria");
    }
}