package com.example.servlets;

import com.example.services.Consulta;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/consulta")
public class ConsultaServlet extends HttpServlet {
    private Consulta consulta = new Consulta();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String bookId = req.getParameter("book");
        String genreId = req.getParameter("genre");
        String authorId = req.getParameter("author");
        String editorialId = req.getParameter("editorial");

        if (bookId != null && !bookId.isEmpty()) {
            req.setAttribute("results", consulta.getAllBooks());
        } else if (genreId != null && !genreId.isEmpty()) {
            req.setAttribute("results", consulta.getBooksByGenre(Integer.parseInt(genreId)));
        }
        // Handle authors, editorials, etc.

        req.getRequestDispatcher("/consulta.jsp").forward(req, resp);
    }
}