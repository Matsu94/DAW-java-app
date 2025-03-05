<%@ page import="java.sql.*" %>
<%@ page import="project.Connexio" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Consulta</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>

<body>
    <div class="container my-5">
        <h1 class="text-center">Consulta Biblioteca</h1>

        <!-- Input Form -->
        <form action="" method="GET" class="mt-4 rounded bg-secondary text-white p-2">
            <div class="mb-3">
                <label for="book" class="form-label">I want to know about this book:</label>
                <select name="book" id="book" class="form-select">
                    <option value="">-- Select a book --</option>
                    <% try (Connection conn = Connexio.getConnection();
                           Statement stmt = conn.createStatement();
                           ResultSet rs = stmt.executeQuery("SELECT id, titol FROM llibres")) {
                           while (rs.next()) {
                               out.println("<option value='" + rs.getInt("id") + "'>" + rs.getString("titol") + "</option>");
                           }
                       } catch (Exception e) {
                           out.println("<option disabled>Error loading books</option>");
                       }
                    %>
                </select>
            </div>
            <div class="mb-3">
                <label for="genre" class="form-label">I want to know the books of this genre:</label>
                <select name="genre" id="genre" class="form-select">
                    <option value="">-- Select a genre --</option>
                    <% try (Connection conn = Connexio.getConnection();
                           Statement stmt = conn.createStatement();
                           ResultSet rs = stmt.executeQuery("SELECT id, nom FROM generes")) {
                           while (rs.next()) {
                               out.println("<option value='" + rs.getInt("id") + "'>" + rs.getString("nom") + "</option>");
                           }
                       } catch (Exception e) {
                           out.println("<option disabled>Error loading genres</option>");
                       }
                    %>
                </select>
            </div>
            <div class="mb-3">
                <label for="author" class="form-label">I want to know the books of this author:</label>
                <select name="author" id="author" class="form-select">
                    <option value="">-- Select an author --</option>
                    <% try (Connection conn = Connexio.getConnection();
                           Statement stmt = conn.createStatement();
                           ResultSet rs = stmt.executeQuery("SELECT id, nom FROM autors")) {
                           while (rs.next()) {
                               out.println("<option value='" + rs.getInt("id") + "'>" + rs.getString("nom") + "</option>");
                           }
                       } catch (Exception e) {
                           out.println("<option disabled>Error loading authors</option>");
                       }
                    %>
                </select>
            </div>
            <div class="mb-3">
                <label for="editorial" class="form-label">I want to know the books of this editorial:</label>
                <select name="editorial" id="editorial" class="form-select">
                    <option value="">-- Select an editorial --</option>
                    <% try (Connection conn = Connexio.getConnection();
                           Statement stmt = conn.createStatement();
                           ResultSet rs = stmt.executeQuery("SELECT id, nom FROM editorials")) {
                           while (rs.next()) {
                               out.println("<option value='" + rs.getInt("id") + "'>" + rs.getString("nom") + "</option>");
                           }
                       } catch (Exception e) {
                           out.println("<option disabled>Error loading editorials</option>");
                       }
                    %>
                </select>
            </div>
            <button type="submit" class="btn btn-primary">Submit</button>
        </form>

        <!-- Results -->
        <div class="mt-5">
            <h2>Results:</h2>
            <ul>
                <%
                    String bookId = request.getParameter("book");
                    String genreId = request.getParameter("genre");
                    String authorId = request.getParameter("author");
                    String editorialId = request.getParameter("editorial");

                    String query = null;
                    if (bookId != null && !bookId.isEmpty()) {
                        query = "SELECT * FROM llibres WHERE id = ?";
                    } else if (genreId != null && !genreId.isEmpty()) {
                        query = "SELECT l.* FROM llibres l JOIN llibre_genere lg ON l.id = lg.id_llibre WHERE lg.id_genere = ?";
                    } else if (authorId != null && !authorId.isEmpty()) {
                        query = "SELECT l.* FROM llibres l JOIN llibre_autor la ON l.id = la.id_llibre WHERE la.id_autor = ?";
                    } else if (editorialId != null && !editorialId.isEmpty()) {
                        query = "SELECT * FROM llibres WHERE id_editorial = ?";
                    }

                    if (query != null) {
                        try (Connection conn = Connexio.getConnection();
                             PreparedStatement stmt = conn.prepareStatement(query)) {
                            if (bookId != null && !bookId.isEmpty())
                                stmt.setInt(1, Integer.parseInt(bookId));
                            if (genreId != null && !genreId.isEmpty())
                                stmt.setInt(1, Integer.parseInt(genreId));
                            if (authorId != null && !authorId.isEmpty())
                                stmt.setInt(1, Integer.parseInt(authorId));
                            if (editorialId != null && !editorialId.isEmpty())
                                stmt.setInt(1, Integer.parseInt(editorialId));

                            try (ResultSet rs = stmt.executeQuery()) {
                                while (rs.next()) {
                                    out.println("<li>" + rs.getString("titol") + " (" + rs.getString("isbn") + ")</li>");
                                }
                            }
                        } catch (Exception e) {
                            out.println("<p class='text-danger'>Error: " + e.getMessage() + "</p>");
                        }
                    } else {
                        out.println("<p class='text-warning'>No selection made</p>");
                    }
                %>
            </ul>
        </div>
    </div>
</body>

</html>
