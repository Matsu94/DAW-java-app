package project;

/**
 * Represents a book in the library system.
 */
public class Book {
    private int id;
    private String titol;
    private String isbn;
    private int anyPublicacio;
    private int idEditorial;

    /**
     * Default constructor.
     */
    public Book() {}

    /**
     * Parameterized constructor.
     * @param id The book's ID.
     * @param titol The book's title.
     * @param isbn The book's ISBN.
     * @param anyPublicacio The book's publication year.
     * @param idEditorial The ID of the book's editorial.
     */
    public Book(int id, String titol, String isbn, int anyPublicacio, int idEditorial) {
        this.id = id;
        this.titol = titol;
        this.isbn = isbn;
        this.anyPublicacio = anyPublicacio;
        this.idEditorial = idEditorial;
    }

    // Getters and setters with Javadoc
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getTitol() { return titol; }
    public void setTitol(String titol) { this.titol = titol; }
    public String getIsbn() { return isbn; }
    public void setIsbn(String isbn) { this.isbn = isbn; }
    public int getAnyPublicacio() { return anyPublicacio; }
    public void setAnyPublicacio(int anyPublicacio) { this.anyPublicacio = anyPublicacio; }
    public int getIdEditorial() { return idEditorial; }
    public void setIdEditorial(int idEditorial) { this.idEditorial = idEditorial; }
}