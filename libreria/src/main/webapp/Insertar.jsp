<%@ page import="java.sql.*" %>
<%@ page import="project.Connexio" %>

<%
    String titol = request.getParameter("titol");
    String isbn = request.getParameter("isbn");
    String anyPublicacioParam = request.getParameter("any_publicacio");
    String idEditorialParam = request.getParameter("id_editorial");

    if (titol != null && isbn != null && anyPublicacioParam != null && idEditorialParam != null) {
        int anyPublicacio = Integer.parseInt(anyPublicacioParam);
        int idEditorial = Integer.parseInt(idEditorialParam);

        try (Connection conn = Connexio.getConnection()) {
            String sql = "INSERT INTO llibres (titol, isbn, any_publicacio, id_editorial) VALUES (?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, titol);
                stmt.setString(2, isbn);
                stmt.setInt(3, anyPublicacio);
                stmt.setInt(4, idEditorial);
                stmt.executeUpdate();
                out.println("<h1>Libro insertado correctamente</h1>");
            }
        } catch (SQLException e) {
            out.println("<h1>Error al insertar el libro: " + e.getMessage() + "</h1>");
            e.printStackTrace();
        }
    } else {
        out.println("<h1>Por favor, completa todos los campos.</h1>");
    }
%>
